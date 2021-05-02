package jp.sacredsanctuary.fifthgenerationexperience

enum class NetworkType(val networkType: Int, val networkName: String) {
    NETWORK_TYPE_UNKNOWN(0, "UNKNOWN"),
    NETWORK_TYPE_GPRS(1, "GPRS"),
    NETWORK_TYPE_EDGE(2, "EDGE"),
    NETWORK_TYPE_UMTS(3, "UMTS"),
    NETWORK_TYPE_CDMA(4, "CDMA"),
    NETWORK_TYPE_EVDO_0(5, "EVDO_0"),
    NETWORK_TYPE_EVDO_A(6, "EVDO_A"),
    NETWORK_TYPE_1XRTT(7, "1XRTT"),
    NETWORK_TYPE_HSDPA(8, "HSDPA"),
    NETWORK_TYPE_HSUPA(9, "HSUPA"),
    NETWORK_TYPE_HSPA(10, "HSPA"),
    NETWORK_TYPE_IDEN(11, "IDEN"),
    NETWORK_TYPE_EVDO_B(12, "EVDO_B"),
    NETWORK_TYPE_LTE(13, "LTE"),
    NETWORK_TYPE_EHRPD(14, "EHRPD"),
    NETWORK_TYPE_HSPAP(15, "HSPAP"),
    NETWORK_TYPE_GSM(16, "GSM"),
    NETWORK_TYPE_TD_SCDMA(17, "TD SCDMA"),
    NETWORK_TYPE_IWLAN(18, "IWLAN"),
    NETWORK_TYPE_LTE_CA(19, "LTE CA"),
    NETWORK_TYPE_NR(20, "NR"),
    ;

    companion object {
        fun from(networkType: Int) = values().firstOrNull {
            it.networkType == networkType
        }
    }
}