package jp.sacredsanctuary.fifthgenerationexperience

enum class OverrideNetworkType(val overrideNetworkType: Int, val overrideNetworkName: String) {
    OVERRIDE_NETWORK_TYPE_NONE(0, "NONE"),
    OVERRIDE_NETWORK_TYPE_LTE_CA(1, "LTE_CA"),
    OVERRIDE_NETWORK_TYPE_LTE_ADVANCED_PRO(2, "LTE_ADVANCED_PRO"),
    OVERRIDE_NETWORK_TYPE_NR_NSA(3, "NR_NSA"),
    OVERRIDE_NETWORK_TYPE_NR_NSA_MMWAVE(4, "NR_NSA_MMWAVE"),
    ;

    companion object {
        fun from(overrideNetworkType: Int) = values().firstOrNull {
            it.overrideNetworkType == overrideNetworkType
        }
    }
}