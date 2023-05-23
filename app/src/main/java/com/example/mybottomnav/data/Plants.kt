package com.example.mybottomnav.data

object Plants {
    fun getPlantData(): List<Plant> {
        val listPlant = mutableListOf<Plant>()
        listPlant.add(
            Plant(
                "https://www.pngmart.com/files/1/Tomato-Vegetable-PNG.png",
                "Tomat",
                "Tomat atau rangam (Solanum lycopersicum) adalah tumbuhan dari keluarga Solanaceae, tumbuhan asli Amerika Tengah dan Selatan, dari Meksiko sampai Peru. Tomat merupakan tumbuhan siklus hidup singkat, dapat tumbuh setinggi 1 sampai 3 meter. Tumbuhan ini memiliki buah berwarna hijau, kuning, dan merah yang biasa dipakai sebagai sayur dalam masakan atau dimakan secara langsung tanpa diproses. Tomat memiliki batang dan daun yang tidak dapat dikonsumsi karena masih sekeluarga dengan kentang dan terung yang mengadung alkaloid.",
            )
        )
        listPlant.add(
            Plant(
                "https://png.pngtree.com/png-clipart/20220829/ourmid/pngtree-cayenne-peppers-png-image_6129542.png",
                "Cabai",
                "Cabai adalah buah dan tumbuhan anggota genus Capsicum. Buahnya dapat digolongkan sebagai sayuran maupun bumbu, tergantung bagaimana pemanfaatannya. Sebagai bumbu, buah cabai yang pedas sangat populer di Asia Tenggara sebagai penguat rasa untuk makanan. Bagi seni masakan Padang, cabai bahkan dianggap sebagai \"bahan makanan pokok\" kesepuluh (alih-alih kesembilan). Sangat sulit bagi masakan Padang dibuat tanpa cabai.",
            )
        )
        listPlant.add(
            Plant(
                "https://www.pngplay.com/wp-content/uploads/4/Spinach-PNG-HD-Quality.png",
                "Bayam",
                "Bayam (Amaranthus) adalah tumbuhan yang biasa ditanam untuk dikonsumsi daunnya sebagai sayuran hijau. Tumbuhan ini berasal dari Amerika tropik namun sekarang tersebar ke seluruh dunia. Tumbuhan ini dikenal sebagai sayuran sumber zat besi yang penting bagi tubuh.",
            )
        )
        listPlant.add(
            Plant(
                "https://www.pngarts.com/files/8/Vegetable-Celery-PNG-Transparent-Image-.png",
                "Seledri",
                "Seledri (Apium graveolens L.) adalah sayuran daun dan tumbuhan obat yang biasa digunakan sebagai bumbu masakan. Beberapa negara termasuk Jepang, Cina dan Korea mempergunakan bagian tangkai daun sebagai bahan makanan. Di Indonesia tumbuhan ini diperkenalkan oleh penjajah Belanda dan digunakan daunnya untuk menyedapkan sup atau sebagai lalap.",
            )
        )
        return listPlant
    }

}