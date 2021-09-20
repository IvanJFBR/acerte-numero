package com.ivstech.acerteonumero.type

enum class FontSizes(val value: Int) {
    FontSize1(0),
    FontSize2(1),
    FontSizeStandard(2),
    FontSize3(3),
    FontSize4(4);

    companion object {
        private val map = values().associateBy(FontSizes::value)
        fun fromInt(fontSizes: Int) = map[fontSizes]
    }
}