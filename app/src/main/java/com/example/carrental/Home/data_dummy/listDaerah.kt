package com.example.carrental.Home.data_dummy

data class Lokasi(
    val provinsi: String,
    val kota: String,
    val kecamatan: List<String>
)

val DaftarLocasi = listOf(
    Lokasi(
        provinsi = "Jawa Barat",
        kota = "Bogor",
        kecamatan = listOf("Bogor Selatan", "Bogor Timur", "Bogor Tengah", "Bogor Barat", "Tanah Sareal", "Citeureup", "Cibinong", "Babakan Madang", "Ciawi", "Cisarua", "Megamendung", "Ciomas", "Dramaga", "Kemang", "Parung", "Ciseeng", "Gunung Sindur", "Rumpin", "Jasinga", "Tenjo", "Parung Panjang", "Cigudeg", "Sukajaya", "Nanggung", "Leuwiliang", "Leuwisadeng", "Pamijahan")
    ),
    Lokasi(
        provinsi = "DKI Jakarta",
        kota = "Jakarta Selatan",
        kecamatan = listOf("Kebayoran Baru", "Kebayoran Lama", "Pesanggrahan", "Cilandak", "Pasar Minggu", "Jagakarsa", "Mampang Prapatan", "Pancoran", "Tebet", "Setiabudi")
    ),
    Lokasi(
        provinsi = "DKI Jakarta",
        kota = "Jakarta Pusat",
        kecamatan = listOf("Gambir", "Sawah Besar", "Kemayoran", "Senen", "Cempaka Putih", "Johar Baru", "Menteng", "Tanah Abang")
    ),
    Lokasi(
        provinsi = "Jawa Tengah",
        kota = "Semarang",
        kecamatan = listOf("Semarang Tengah", "Semarang Utara", "Semarang Timur", "Semarang Selatan", "Semarang Barat", "Gajahmungkur", "Gayamsari", "Genuk", "Gunungpati", "Mijen", "Ngaliyan", "Pedurungan", "Tembalang", "Tugu", "Banyumanik", "Candisari")
    ),
    Lokasi(
        provinsi = "Jawa Tengah",
        kota = "Surakarta",
        kecamatan = listOf("Banjarsari", "Jebres", "Laweyan", "Pasar Kliwon", "Serengan")
    ),
    Lokasi(
        provinsi = "Jawa Timur",
        kota = "Surabaya",
        kecamatan = listOf("Gubeng", "Sukolilo", "Rungkut", "Wonokromo", "Sawahan", "Tandes", "Benowo", "Pakal", "Lakarsantri", "Sambikerep", "Asemrowo", "Pabean Cantikan", "Semampir", "Bulak", "Kenjeran", "Tambaksari", "Mulyorejo", "Sukomanunggal", "Tegalsari", "Genteng", "Bubutan", "Krembangan", "Wiyung", "Karangpilang", "Jambangan", "Gayungan", "Dukuh Pakis", "Gunung Anyar", "Tenggilis Mejoyo", "Simokerto")
    ),
    Lokasi(
        provinsi = "DI Yogyakarta",
        kota = "Yogyakarta",
        kecamatan = listOf("Gondokusuman", "Danurejan", "Kotagede", "Umbulharjo", "Mergangsan", "Mantrijeron", "Ngampilan", "Wirobrajan", "Tegalrejo", "Jetis", "Gedongtengen", "Pakualaman", "Kraton", "Mergangsan")
    )
)