package com.example.myapplication

class OSSUtil {
    companion object {
        val suffx = "?x-oss-process=image/"

        fun isAddScale(picUrl: String): Boolean {
            return picUrl.contains(suffx)
        }

        fun isOSSUrl(picUrl: String): Boolean {
            return true
        }

        fun addScale(picUrl: String, width: Int, height: Int): String {
            return if (!isAddScale(picUrl)) {
                picUrl + suffx + "resize,m_lfit,h_$height,w_$width"
            } else {
                picUrl
            }
        }

        fun getScaleWidthAndHeight(picUrl: String): IntArray? {
            return Regex("resize,m_lfit,h_(\\d+),w_(\\d+)").find(picUrl)?.groupValues?.let {
                return@let intArrayOf(it[1].toInt(), it[2].toInt())
            }
        }
    }
}