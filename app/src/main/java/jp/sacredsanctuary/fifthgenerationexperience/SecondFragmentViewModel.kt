package jp.sacredsanctuary.fifthgenerationexperience

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Store the information for SecondFragmentViewModel Data.
 */
class SecondFragmentViewModel : ViewModel() {
    private val _networkType = MutableLiveData<NetworkType>()
    val networkType: LiveData<NetworkType> = _networkType
    fun networkType(networkType: NetworkType?) {
        if (networkType != null) {
            _networkType.postValue(networkType)
        }
    }

    private val _overrideNetworkType = MutableLiveData<OverrideNetworkType>()
    val overrideNetworkType: LiveData<OverrideNetworkType> = _overrideNetworkType
    fun overrideNetworkType(overrideNetworkType: OverrideNetworkType?) {
        if (overrideNetworkType != null) {
            _overrideNetworkType.postValue(overrideNetworkType)
        }
    }

    private val _isUnmetered = MutableLiveData<Boolean>(false)
    val isUnmetered: LiveData<Boolean> = _isUnmetered
    fun isUnmetered(unmetered: Boolean) {
        _isUnmetered.postValue(unmetered)
    }

    private val _linkDownBandwidthKbps = MutableLiveData<Int>(0)
    val linkDownBandwidthKbps: LiveData<Int> = _linkDownBandwidthKbps
    fun linkDownBandwidthKbps(linkDownBandwidthKbps: Int) {
        _linkDownBandwidthKbps.postValue(linkDownBandwidthKbps)
    }

    private val _linkUpBandwidthKbps = MutableLiveData<Int>(0)
    val linkUpBandwidthKbps: LiveData<Int> = _linkUpBandwidthKbps
    fun linkUpBandwidthKbps(linkDownBandwidthKbps: Int) {
        _linkUpBandwidthKbps.postValue(linkDownBandwidthKbps)
    }
}