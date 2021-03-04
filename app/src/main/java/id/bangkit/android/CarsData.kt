package id.bangkit.android

object CarsData {
    private val carNames = arrayOf(
        "Nissan Magnite",
        "Nissan Kicks",
        "Nissan Navara",
        "Nissan Livina",
        "Nissan Serena",
        "Nissan Terra",
        "Nissan X-Trail",
        "Nissan Juke",
        "Nissan March",
        "Nissan Evalia")

    private val carModel = arrayOf(
        "Model Baru",
        "Model Baru",
        "Model Baru",
        "Update Harga",
        "Update Harga",
        "Generasi Terbaru",
        "Generasi Terbaru",
        "Generasi Terbaru",
        "Model Baru",
        "Model Baru"
    )

    private val carTransmition = arrayOf(
        "Manual",
        "Otomatis",
        "Otomatis",
        "Manual",
        "Manual",
        "Manual",
        "Otomatis",
        "Otomatis",
        "Otomatis",
        "Otomatis"
    )

    private val carBodyType = arrayOf(
        "SUP",
        "SUP",
        "Pickup",
        "MPV",
        "MPV",
        "SUP",
        "SUP",
        "SUP",
        "Wagon",
        "MPV"
    )

    private val carPrice = arrayOf(
        "Rp 208 Juta - Rp 238 Juta",
        "Rp 449 Juta",
        "Rp 448 Juta",
        "Rp 208 Juta - Rp 272 Juta",
        "Rp 463 Juta - Rp 481 Juta",
        "Rp 478 Juta - Rp 608 Juta",
        "Rp 547 Juta",
        "Rp 303 Juta",
        "Rp 185 Juta",
        "Rp 158 Juta"
    )

    private val carDesc = arrayOf(
        "Dengan kesuksesan Nissan Magnite di India. Pihak developer mengambil langkah tempat dengan resmi merilis Magnite untuk pasaran otomotif Indonesia. Ia dirilis dengan spesifikasi fitur maupun sistem mesin yang sangat tangguh. Tidak mengherankan respon pasaran otomotif cenderung ramai memberikan penilaian positif. Kompak SUV yang meluncur pada 21 Desember tahun lalu ini memang menawarkan desain eksterior sporty untuk para peminat otomotif.",
        "Konsep Nissan Kicks sejatinya pertama kali diperkenalkan di tahun 2014 di ajang São Paulo International Motor Show. Pada saat itu, Nissan mencoba menggaet pasaran Amerika Latin dengan membuat mobil crossover SUV dengan bentuk yang compact yang diklaim pabrikan ini mengingatkan mereka pada jalanan di Brazil.\n",
        "Inilah Nissan Navara, pick up double cabin yang memiliki tampang maskulin dan gagah, sekaligus desain eksterior mewah dan kekinian. Andalkan performanya yang tangguh menembus berbagai medan menantang berkat mesin tipe YD25DDTi Common Rail Turbo Diesel With Intercoller berkubikasi 2.488 cc. SUV pick up ini mampu menyemburkan tenaga besar dan torsi yang besar. Tidak mengherankan, Nissan Navara merupakan hasil kolaborasi Renault - Nissan mampu menghasilkan kekuatan tangguh yang telah teruji.",
        "Nissan Grand Livina tampil dalam wajah baru melalui nama All New Livina 2019. Generasi anyar Grand Livina ini terhitung masih mobil baru di tahun 2019, PT. Nissan Motor Indonesia (NMI) resmi mengumumkan kehadiran All New Livina ini pada pertengahan Februari 2019 lalu. Menariknya, Low MPV andalan Nissan ini benar-benar mengalami perombakan besar-besaran dengan wajah baru dan juga spesifikasi mesin yang digunakan.",
        "Mobil Nissan Serena dengan model MPV ini sudah mulai aktif diproduksi sejak tahun 91. Tak hanya nyaman, mobil yang satu ini membuat supirnya tak kalah senang. Saat ini, Nissan Serena juga diproduksi baru lagi dan menghadirkan adanya teknologi baru yang membuatnya semakin melejit dibandingkan saingan mobil sekelasnya",
        "Banyak orang kaya membeli mobil SUV sekelas Toyota Fortuner dan Mitsubishi Pajero Sport. namun kali ini Nissan mengejarnya dengan rilis SUV baru bernama Nissan Terra. Interior yang mewah dan desain eksterior yang gagah, menjadi salah satu senjata Nissan menghadapi kompetitornya.",
        "Nissan mampu menyedot perhatian melalui Nissan X-Trail yang berdesain modern sekaligus tangguh. Bahkan Nissan X-Trail menjadi SUV pertama yang memiliki varian hybrid yang dibanderol dengan harga di bawah Rp1 miliar. Di Indonesia, Nissan X-Trail juga menjadi SUV pertama yang mengadopsi sistem transmisi otomatis CVT. All-New Nissan X-Trail memenuhi kebutuhan konsumen terhadap mobil SUV yang tangguh, nyaman, stylish, dan juga memiliki harga terjangkau di kelasnya.",
        "Crossover dengan desain mencengangkan ini mungkin pertama kali dipelopori oleh Nissan JUKE. Bagaimana tidak mobil bertipe compact cross-over ini memiliki penampilan yang tidak biasa. Nissan membesut Nissan JUKE dengan desain mobil SUV nan sporty yang berbeda dibandingkan mobil setipe lainnya. Tidak biasa dan memiliki struktur kekar dan besar. Tidak heran desain Nissan JUKE cukup kontroversial dan mendapatkan sorotan positif maupun negatif dari pengamat otomotif.",
        "Nissan March merupakan small hatchback yang memiliki penampilan mungil dan fashionable. Dikenal sebagai city car keluaran Nissan yang lincah menembus kondisi jalanan perkotaan di Indonesia yang terkenal macet. Di negeri asalnya, Jepang, Nissan March juga dikenal sebagai Nissan Micra. Meski pamornya saat ini tidak lama secermelang city car lainnya seperti Honda Brio atau Mitsubishi Mirage, namun Nissan March sebenarnya memiliki semua hal berkelas dan fitur-fitur canggih telah dimiliki oleh city car saat ini.",
        "Evalia sendiri diluncurkan pertama kali pada tahun 2012 yang lalu. Kala itu, Nissan mencoba memberikan alternatif lain di segmen MPV setelah sebelumnya mereka memiliki Grand Livina yang memiliki porsi pasar cukup besar di segmen low MPV.\n" +
                "\n" +
                "Kehadiran Nissan Evalia ini juga tak luput dari minat publik pada kendaraan jenis medium MPV yang memiliki bodi lebih besar dari low MPV, terutama semenjak kemunculan kompetitor Daihatsu Luxio serta Gran Max."
    )

    private val carImages = intArrayOf(R.drawable.satu,
        R.drawable.car_two,
        R.drawable.car_three,
        R.drawable.car_four,
        R.drawable.car_five,
        R.drawable.car_six,
        R.drawable.car_seven,
        R.drawable.car_eight,
        R.drawable.car_nine,
        R.drawable.car_ten)

    val listData: ArrayList<Car>
        get() {
            val list = arrayListOf<Car>()
            for (position in carNames.indices) {
                val car = Car()
                car.carName = carNames[position]
                car.carBodyType = carBodyType[position]
                car.carDescription = carDesc[position]
                car.carImage = carImages[position]
                car.carModel = carModel[position]
                car.carPrice = carPrice[position]
                car.carTransmision = carTransmition[position]
                list.add(car)
            }
            return list
        }
}