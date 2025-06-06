# Body Fat Percentage Calculator

Aplikasi Android untuk menghitung persentase lemak tubuh (Body Fat Percentage) menggunakan arsitektur MVVM + Hilt dengan UI sederhana.

## Fitur Utama

- **Input Data**: Masukkan berat badan (kg), tinggi badan (cm), usia (tahun), dan jenis kelamin
- **Kalkulasi BMI**: Menghitung Body Mass Index berdasarkan berat dan tinggi badan
- **Kalkulasi BFP**: Menghitung Body Fat Percentage menggunakan rumus:
  - Pria: BFP = (1.20 × BMI) + (0.23 × usia) - 16.2
  - Wanita: BFP = (1.20 × BMI) + (0.23 × usia) - 5.4
- **Kategori**: Menampilkan kategori berdasarkan hasil BFP:
  - < 10% (Sangat Rendah)
  - 10-20% (Rendah)
  - 21-25% (Normal)
  - 26-30% (Tinggi)
  - > 30% (Sangat Tinggi)
- **Navigasi**: Hasil ditampilkan di halaman terpisah
- **Info Pengembang**: Ditampilkan sebagai bottom sheet dari bawah

## Arsitektur

Aplikasi ini menggunakan arsitektur **MVVM (Model-View-ViewModel)** dengan **Hilt** untuk dependency injection:

### Struktur Project
```
app/src/main/java/edu/unikom/bodyfatpercentagecalculator/
├── data/
│   ├── model/
│   │   ├── BodyFatData.kt
│   │   ├── BodyFatResult.kt
│   │   └── JenisKelamin.kt
│   └── repository/
│       └── BodyFatRepository.kt
├── ui/
│   └── viewmodel/
│       └── BodyFatViewModel.kt
├── utils/
│   └── BodyFatCalculator.kt
├── BodyFatCalculatorApplication.kt
├── MainActivity.kt
├── ResultActivity.kt
└── InfoPengembangBottomSheet.kt
```

### Komponen Utama

1. **Data Layer**
   - `BodyFatData`: Data class untuk input pengguna
   - `BodyFatResult`: Data class untuk hasil kalkulasi
   - `BodyFatRepository`: Repository untuk mengelola data

2. **Business Logic Layer**
   - `BodyFatCalculator`: Utility class untuk kalkulasi BMI dan BFP
   - `BodyFatViewModel`: ViewModel untuk mengelola state dan business logic

3. **Presentation Layer**
   - `MainActivity`: Activity untuk input data
   - `ResultActivity`: Activity untuk menampilkan hasil
   - `InfoPengembangBottomSheet`: Bottom sheet untuk info pengembang
   - Layout XML dengan desain sederhana

4. **Dependency Injection**
   - `@HiltAndroidApp`: Application class
   - `@AndroidEntryPoint`: MainActivity
   - `@HiltViewModel`: ViewModel
   - `@Singleton`: Repository dan Calculator

## Teknologi yang Digunakan

- **Kotlin**: Bahasa pemrograman utama
- **Android Architecture Components**:
  - ViewModel
  - LiveData
  - ViewBinding
- **Hilt**: Dependency injection
- **Material Design Components**: Bottom sheet untuk info pengembang
- **Coroutines**: Asynchronous programming

## UI Design

- **UI Sederhana**: Menggunakan layout LinearLayout dengan border sederhana
- **Warna Basic**: Background putih dengan border abu-abu
- **Form Input**: EditText sederhana dengan border
- **Navigasi Antar Halaman**: Input di MainActivity, hasil di ResultActivity
- **Bottom Sheet**: Info pengembang muncul dari bawah layar

## Cara Penggunaan

1. **Halaman Input (MainActivity)**:
   - Masukkan berat badan dalam kilogram
   - Masukkan tinggi badan dalam centimeter
   - Masukkan usia dalam tahun
   - Pilih jenis kelamin (Laki-laki/Perempuan)
   - Tekan tombol "Hitung Body Fat Percentage"

2. **Halaman Hasil (ResultActivity)**:
   - Lihat data input yang telah dimasukkan
   - Lihat hasil BMI, BFP, dan kategori
   - Tekan "Info Pengembang" untuk melihat informasi developer
   - Tekan "← Kembali" untuk kembali ke halaman input

3. **Info Pengembang (Bottom Sheet)**:
   - Muncul dari bawah layar
   - Menampilkan informasi pengembang
   - Tekan "Tutup" untuk menutup

## Developer

**NIM**: 10122007  
**Nama**: Mochammad Rizky Firdaus

## Studi Kasus

Aplikasi ini menangani 3 studi kasus sesuai spesifikasi:

1. **Studi Kasus 1**: Pria, 70kg, 175cm, 25 tahun → BMI: 22.86, BFP: 16.98, Kategori: Rendah
2. **Studi Kasus 2**: Wanita, 60kg, 165cm, 30 tahun → BMI: 22.04, BFP: 27.95, Kategori: Tinggi
3. **Studi Kasus 3**: Wanita, 75kg, 170cm, 40 tahun → BMI: 25.30, BFP: 38.96, Kategori: Sangat Tinggi

## Fitur Tambahan

- **Validasi Input**: Memastikan semua data input valid
- **Loading State**: Menampilkan progress bar saat kalkulasi
- **Error Handling**: Menampilkan pesan error jika input tidak valid
- **Data Persistence**: Mengirim data antar Activity menggunakan Parcelable 