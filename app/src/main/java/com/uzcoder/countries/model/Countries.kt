package com.uzcoder.countries.model

import java.io.Serializable

typealias Welcome = ArrayList<WelcomeElement>

data class WelcomeElement (
    val name: Name,
    val tld: List<String>? = null,
    val cca2: String,
    val ccn3: String? = null,
    val cca3: String,
    val cioc: String? = null,
    val independent: Boolean? = null,
    val status: Status,
    val unMember: Boolean,
    val currencies: Currencies? = null,
    val idd: Idd,
    val capital: List<String>? = null,
    val altSpellings: List<String>,
    val region: Region,
    val subregion: String? = null,
    val languages: Map<String, String>? = null,
    val translations: Map<String, Translation>,
    val latlng: List<Double>,
    val landlocked: Boolean,
    val borders: List<String>? = null,
    val area: Double,
    val demonyms: Demonyms? = null,
    val flag: String,
    val maps: Maps,
    val population: Long,
    val fifa: String? = null,
    val car: Car,
    val timezones: List<String>,
    val continents: List<Continent>,
    val flags: Flags,
    val coatOfArms: CoatOfArms,
    val startOfWeek: StartOfWeek,
    val capitalInfo: CapitalInfo,
    val postalCode: PostalCode? = null,
    val gini: Map<String, Double>? = null
):Serializable

data class CapitalInfo (
    val latlng: List<Double>? = null
):Serializable

data class Car (
    val signs: List<String>? = null,
    val side: Side
):Serializable

enum class Side {
    Left,
    Right
}

data class CoatOfArms (
    val png: String? = null,
    val svg: String? = null
):Serializable

enum class Continent {
    Africa,
    Antarctica,
    Asia,
    Europe,
    NorthAmerica,
    Oceania,
    SouthAmerica
}

data class Currencies (
    val kwd: Aed? = null,
    val eur: Aed? = null,
    val tnd: Aed? = null,
    val jpy: Aed? = null,
    val gyd: Aed? = null,
    val gbp: Aed? = null,
    val shp: Aed? = null,
    val gtq: Aed? = null,
    val ves: Aed? = null,
    val cve: Aed? = null,
    val azn: Aed? = null,
    val ggp: Aed? = null,
    val kes: Aed? = null,
    val ssp: Aed? = null,
    val mmk: Aed? = null,
    val chf: Aed? = null,
    val aud: Aed? = null,
    val crc: Aed? = null,
    val egp: Aed? = null,
    val ils: Aed? = null,
    val jod: Aed? = null,
    val xof: Aed? = null,
    val sll: Aed? = null,
    val php: Aed? = null,
    val rub: Aed? = null,
    val htg: Aed? = null,
    val krw: Aed? = null,
    val wst: Aed? = null,
    val awg: Aed? = null,
    val afn: Aed? = null,
    val bsd: Aed? = null,
    val usd: Aed? = null,
    val gel: Aed? = null,
    val kyd: Aed? = null,
    val mkd: Aed? = null,
    val syp: Aed? = null,
    val hkd: Aed? = null,
    val clp: Aed? = null,
    val zwl: Aed? = null,
    val cad: Aed? = null,
    val xaf: Aed? = null,
    val szl: Aed? = null,
    val zar: Aed? = null,
    val omr: Aed? = null,
    val ron: Aed? = null,
    val mzn: Aed? = null,
    val ttd: Aed? = null,
    val bhd: Aed? = null,
    val lbp: Aed? = null,
    val bdt: Aed? = null,
    val etb: Aed? = null,
    val pen: Aed? = null,
    val mur: Aed? = null,
    val cuc: Aed? = null,
    val cup: Aed? = null,
    val vnd: Aed? = null,
    val xcd: Aed? = null,
    val bzd: Aed? = null,
    val cny: Aed? = null,
    val thb: Aed? = null,
    val kmf: Aed? = null,
    val lak: Aed? = null,
    val srd: Aed? = null,
    val gmd: Aed? = null,
    val mvr: Aed? = null,
    val top: Aed? = null,
    val gnf: Aed? = null,
    val ang: Aed? = null,
    val pab: Aed? = null,
    val pgk: Aed? = null,
    val xpf: Aed? = null,
    val khr: Aed? = null,
    val nio: Aed? = null,
    val cop: Aed? = null,
    val mru: Aed? = null,
    val bwp: Aed? = null,
    val fjd: Aed? = null,
    val nok: Aed? = null,
    val bmd: Aed? = null,
    val ugx: Aed? = null,
    val bam: BAM? = null,
    val kzt: Aed? = null,
    val nzd: Aed? = null,
    val sdg: BAM? = null,
    val qar: Aed? = null,
    val bbd: Aed? = null,
    val currenciesTRY: Aed? = null,
    val dkk: Aed? = null,
    val yer: Aed? = null,
    val stn: Aed? = null,
    val tmt: Aed? = null,
    val kpw: Aed? = null,
    val ern: Aed? = null,
    val gip: Aed? = null,
    val mnt: Aed? = null,
    val mwk: Aed? = null,
    val tvd: Aed? = null,
    val mdl: Aed? = null,
    val bnd: Aed? = null,
    val sgd: Aed? = null,
    val ghs: Aed? = null,
    val dop: Aed? = null,
    val czk: Aed? = null,
    val mad: Aed? = null,
    val hnl: Aed? = null,
    val mga: Aed? = null,
    val amd: Aed? = null,
    val fkp: Aed? = null,
    val aoa: Aed? = null,
    val idr: Aed? = null,
    val UZS: Aed,
    val bif: Aed? = null,
    val all: Aed? = null,
    val bob: Aed? = null,
    val kgs: Aed? = null,
    val aed: Aed? = null,
    val pyg: Aed? = null,
    val tzs: Aed? = null,
    val ars: Aed? = null,
    val pkr: Aed? = null,
    val npr: Aed? = null,
    val sek: Aed? = null,
    val uyu: Aed? = null,
    val mxn: Aed? = null,
    val sos: Aed? = null,
    val scr: Aed? = null,
    val btn: Aed? = null,
    val inr: Aed? = null,
    val rwf: Aed? = null,
    val huf: Aed? = null,
    val isk: Aed? = null,
    val fok: Aed? = null,
    val djf: Aed? = null,
    val lrd: Aed? = null,
    val twd: Aed? = null,
    val lyd: Aed? = null,
    val kid: Aed? = null,
    val ckd: Aed? = null,
    val lsl: Aed? = null,
    val myr: Aed? = null,
    val sar: Aed? = null,
    val bgn: Aed? = null,
    val brl: Aed? = null,
    val jep: Aed? = null,
    val dzd: Aed? = null,
    val sbd: Aed? = null,
    val lkr: Aed? = null,
    val ngn: Aed? = null,
    val nad: Aed? = null,
    val byn: Aed? = null,
    val uah: Aed? = null,
    val jmd: Aed? = null,
    val zmw: Aed? = null,
    val irr: Aed? = null,
    val iqd: Aed? = null,
    val imp: Aed? = null,
    val mop: Aed? = null,
    val pln: Aed? = null,
    val rsd: Aed? = null,
    val cdf: Aed? = null,
    val tjs: Aed? = null,
    val vuv: Aed? = null
):Serializable

data class Aed (
    val name: String,
    val symbol: String
):Serializable

data class BAM (
    val name: String
):Serializable

data class Demonyms (
    val eng: Eng,
    val fra: Eng? = null
):Serializable

data class Eng (
    val f: String,
    val m: String
):Serializable

data class Flags (
    val png: String,
    val svg: String,
    val alt: String? = null
):Serializable

data class Idd (
    val root: String? = null,
    val suffixes: List<String>? = null
):Serializable

data class Maps (
    val googleMaps: String,
    val openStreetMaps: String
):Serializable

data class Name (
    val common: String,
    val official: String,
    val nativeName: Map<String, Translation>? = null
):Serializable

data class Translation (
    val official: String,
    val common: String
):Serializable

data class PostalCode (
    val format: String,
    val regex: String? = null
):Serializable

enum class Region {
    Africa,
    Americas,
    Antarctic,
    Asia,
    Europe,
    Oceania
}

enum class StartOfWeek {
    Monday,
    Saturday,
    Sunday
}

enum class Status {
    OfficiallyAssigned,
    UserAssigned
}