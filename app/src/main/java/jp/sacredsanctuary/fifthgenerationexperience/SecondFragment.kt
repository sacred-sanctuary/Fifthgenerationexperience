package jp.sacredsanctuary.fifthgenerationexperience

import android.Manifest
import android.Manifest.permission.READ_PHONE_STATE
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkCapabilities.NET_CAPABILITY_NOT_METERED
import android.net.NetworkCapabilities.NET_CAPABILITY_TEMPORARILY_NOT_METERED
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.telephony.PhoneStateListener
import android.telephony.TelephonyDisplayInfo
import android.telephony.TelephonyManager
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.content.PermissionChecker.PERMISSION_GRANTED
import androidx.core.content.PermissionChecker.checkSelfPermission
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import jp.sacredsanctuary.fifthgenerationexperience.databinding.FragmentSecondBinding


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment(R.layout.fragment_second) {
    private val viewModel: SecondFragmentViewModel by viewModels()
    private var binding: FragmentSecondBinding? = null
    private var connectivityManager : ConnectivityManager? = null

    private val phoneStateListener = object : PhoneStateListener() {
        override fun onDisplayInfoChanged(displayInfo: TelephonyDisplayInfo) {
            viewModel.networkType(NetworkType.from(displayInfo.networkType))
            viewModel.overrideNetworkType(OverrideNetworkType.from(displayInfo.overrideNetworkType))
        }
    }

    private val networkCallback = object : ConnectivityManager.NetworkCallback() {
        override fun onCapabilitiesChanged(
            network: Network,
            networkCapabilities: NetworkCapabilities
        ) {
            viewModel.isUnmetered(networkCapabilities.hasCapability(NET_CAPABILITY_NOT_METERED)
                    || networkCapabilities.hasCapability(NET_CAPABILITY_TEMPORARILY_NOT_METERED))

            viewModel.linkDownBandwidthKbps(networkCapabilities.linkDownstreamBandwidthKbps)
            viewModel.linkUpBandwidthKbps(networkCapabilities.linkUpstreamBandwidthKbps)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentSecondBinding.bind(view).also {
            it.viewModel = viewModel
            it.lifecycleOwner = viewLifecycleOwner
            it.buttonSecond.setOnClickListener {
                findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
            }
        }
        connectivityManager = requireContext().getSystemService(Context.CONNECTIVITY_SERVICE) as? ConnectivityManager
        connectivityManager?.registerDefaultNetworkCallback(networkCallback)
    }

    override fun onResume() {
        super.onResume()
        when {
            checkSelfPermission(requireContext(), READ_PHONE_STATE) == PERMISSION_GRANTED -> {
                val telephonyManager = requireContext().getSystemService(TelephonyManager::class.java)
                telephonyManager.listen(
                    phoneStateListener,
                    PhoneStateListener.LISTEN_DISPLAY_INFO_CHANGED
                )
            }
            shouldShowRequestPermissionRationale(Manifest.permission.READ_PHONE_STATE) -> {
                AlertDialog.Builder(requireContext())
                    .setMessage(R.string.permission_message)
                    .setPositiveButton(R.string.settings) { _, _ ->
                        val intent = Intent().apply {
                            action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                            data = Uri.fromParts(PACKAGE_SCHEME, requireContext().packageName, null)
                            flags = Intent.FLAG_ACTIVITY_NEW_TASK
                        }
                        startActivity(intent)
                    }
                    .setNegativeButton(R.string.cancel) { _, _ ->

                    }
                    .create()
                    .show()
            }
            else -> {
                requestPermissions(
                    arrayOf(READ_PHONE_STATE),
                    PERMISSION_REQUEST_CODE
                )
            }
        }
    }

    override fun onPause() {
        super.onPause()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val telephonyManager = requireContext().getSystemService(TelephonyManager::class.java)
            telephonyManager.listen(phoneStateListener, PhoneStateListener.LISTEN_NONE)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
        connectivityManager?.unregisterNetworkCallback(networkCallback)
    }

    companion object {
        private const val PERMISSION_REQUEST_CODE = 0
        private const val PACKAGE_SCHEME = "package"
    }
}