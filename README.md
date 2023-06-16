<h1 align="center">
  <img align="center" src="Image/App Logo.png"  width="500"></img>
<br>
BotaniPlan Mobile Development Readme
</h1>
<!-- <div align="center"> -->

# Team Profile 

### Team ID : C23-PS261

### Team Members : 
* (ML) M305DSY2355 – Devina Margarita – Universitas Pembangunan Nasional “Veteran” Jawa Timur 
* (ML) M159DSX0188 – Michael Eko Hartono – Universitas Ciputra
* (CC) C058DSX2777 – M Fahmi Alfaris – Politeknik Negeri Banyuwangi
* (CC) C151DKX3960 – Muhammad Helmi Yahya – Universitas Brawijaya
* (MD) A151DSX2280 – Raya Aldyen Dessario – Universitas Brawijaya
* (MD) A151DSX2429 – Elang Sotya Putra Dumipta – Universitas Brawijaya

### Role
* Project Plan (M305DSY2355 – Devina Margarita, M159DSX0188 – Michael Eko Hartono, C058DSX2777 – M Fahmi Alfaris, C151DKX3960 – Muhammad Helmi Yahya, A151DSX2280 – Raya Aldyen Dessario, A151DSX2429 – Elang Sotya Putra Dumipta)
* UI/UX (A151DSX2280 – Raya Aldyen Dessario, A151DSX2429 – Elang Sotya Putra Dumipta)
* Build Machine Learning Model (M305DSY2355 – Devina Margarita, M159DSX0188 – Michael Eko Hartono)
* Cloud Architecture Development (C058DSX2777 – M Fahmi Alfaris, C151DKX3960 – Muhammad Helmi Yahya)
* Android Apps Development (A151DSX2280 – Raya Aldyen Dessario, A151DSX2429 – Elang Sotya Putra Dumipta)

# BotaniPlan Mobile Development Project 
This Project is our final project for Bangkit Academy 2023

**Project Background :**
 The agricultural industry in Indonesia makes a large contribution to the national economy, but there are still many novice farmers who experience difficulties in managing their agricultural business due to limited knowledge and experience in farming. The data shows that the majority of workers in the agricultural sector have education below the senior high school level, resulting in the emergence of a new group of inexperienced farmers. Based on BPS data (2018), the main income of the Indonesian population in 73 thousand villages (87%) comes from the agricultural sector

One possible solution is to apply machine learning based mobile application and data analysis to recommend the most suitable types of horticultural crops to grow in the region, based on factors such as environmental conditions. Additionally, a market price prediction model can be developed to assist farmers in making informed decisions regarding the sale of horticultural products. This model can utilize various data sources such as market demand and the selling prices of previous and current products in the farmer’s regions. By implementing this solution, it is hoped that farmers can maximize the potential of their agricultural land, improve the quality and selling value of horticultural products in the market, and increase price stability in the market.

**Android Development**
BotaniPlan app is used to interact with users to provide recommendations related to horticultural crops that are suitable for the geographical conditions of the region and can also help users predict the market price of a horticultural crop. The form of user interaction in this application is that the user only presses one button to look for suitable plant recommendations based on the conditions of the area, and after the user presses the button, the application system will send the coordinates of the user's location to get the geographical conditions of the area and display the recommended crop results. To predict plant prices, the user only needs to fill in the type of plant, harvested area, harvested quantity, and area in the existing fields, and the system will return data in the form of a predicted price. Results from plant recommendations and price predictions will be returned by using Retrofit.

**Case**

- [x] Horticultural plant recommendation (pepper, shallots, garlic, cabbage, and potatoes)
- [x] Horticultural market price prediction

## ScreenShots
<p align="center">
  <img src="Image/Page Login.jpg" width="200">
  <img src="Image/Page SignUp.jpg" width="200">
</p>

<p align="center">
  <img src="Image/Page Home.jpg" width="200">
  <img src="Image/Page Fitur Utama.jpg" width="200">
  <img src="Image/Page Fitur Akun.jpg" width="200">
</p>

<p align="center">
  <img src="Image/Page Mulai Rekomendasi.jpg" width="200">
  <img src="Image/Page Hasil Rekomendasi.jpg" width="200">
  <img src="Image/Page Detail Tanaman.jpg" width="200">
</p>

<p align="center">
  <img src="Image/Page Prediksi Harga.jpg" width="200">
  <img src="Image/Hasil Prediksi Harga.jpg" width="200">
</p>

## Development Roadmap
- [x] [Kotlin](https://kotlinlang.org/)
- [x] [Retrofit](https://square.github.io/retrofit/)
- [x] [HttpURLConnection](https://developer.android.com/reference/java/net/HttpURLConnection)
- [x] [SharedPreferences](https://developer.android.com/training/data-storage/shared-preferences?hl=id)
- [x] [Navigation Component](https://developer.android.com/guide/navigation/navigation-getting-started)

## Features
- [x] Login
- [x] Register
- [x] Bottom Navigation
- [x] Current Weather Feature
- [x] Horticultural Plant Recommendation Feature
- [x] Horticultural Plant Price Prediction Feature
- [x] Horticultural Plant Details Feature
- [x] Vegetable Prediction Feature
- [x] Information Feature of the Most consumed Horticultural Plants

## Requirement
* Android Studio Chipmunk
* Android Device or Android Emulator with minimum Nougat Version
* Emulator / External Device
* USB Cable (to Connect Android Device to your Computer)

### 1. Clone this Project to your Computer
```bash
git clone https://github.com/rayaaldyen/capstone-project
```

or you can use Android Studio 

Choose File > New > Project from Version Control ...

### 2. Open the Project in your Android Studio
Open Android Studio and select open an existing project.

### 3. Run Project in Android Studio
Wait for Gradle Build to Finish and then click the `Run > Run ‘app’`. Now the BotaniPlan app has been installed in your emulator or phone. Please make sure that you have configured your android device or emulator
