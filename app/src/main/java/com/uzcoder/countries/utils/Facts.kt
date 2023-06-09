package com.uzcoder.countries.utils

import com.uzcoder.countries.model.Fact

object Facts {
    fun uzbekistan() : ArrayList<Fact> {
        val facts : ArrayList<Fact> = ArrayList()
        facts.add(Fact("Registan","https://storage.yandexcloud.net/storage.yasno.media/nat-geo/images/2019/5/16/dba18beab6e8489c9994493a84838fa7.max-2500x1500.jpg"))
        facts.add(Fact("Shahrisabz","https://as2.ftcdn.net/v2/jpg/03/00/44/15/1000_F_300441563_UQJMpOf5RyVVRjGwvRXaZgA7T1VKy1YI.jpg"))
        facts.add(Fact("Buhoro","https://mediaim.expedia.com/destination/1/bc97c15b32d7c7b3c40c5d7dbffa4387.jpg?impolicy=fcrop&w=1040&h=580&q=mediumHigh"))
        facts.add(Fact("Xiva","https://wikiway.com/upload/resize_cache/hl-photo/813/547/720_400_1/khiva_83.jpg"))
        facts.add(Fact("Samarqand","https://i0.wp.com/diplomatmagazine.eu/wp-content/uploads/photo-1-1.jpg?fit=700%2C442&ssl=1"))
        return facts
    }
}