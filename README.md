# Modul-1-Coding-Standards

# Reflection 1
1. Prinsip Clean Code yang Diterapkan
Selama pengerjaan fitur Create, Read, Edit, dan Delete (CRUD), berikut adalah prinsip Clean Code yang saya terapkan:

Meaningful Names: Saya menggunakan penamaan variabel dan metode yang deskriptif. Contohnya, findAll(), findById(), update(), dan delete() pada layer Service dan Repository memudahkan pembaca memahami tujuan dari setiap fungsi tanpa memerlukan banyak komentar.

Separation of Concerns: Struktur proyek memisahkan tanggung jawab dengan jelas menggunakan pola MVC. Controller hanya menangani HTTP request, Service menangani logika bisnis, dan Repository fokus pada manajemen data. Hal ini membuat kode lebih mudah diuji (testable) dan dipelihara.

DRY (Don't Repeat Yourself): Penggunaan library Lombok (seperti @Getter dan @Setter) membantu menghilangkan kode boilerplate yang berulang. Selain itu, logika manipulasi list dipusatkan di Repository sehingga tidak ada duplikasi logika di layer Service.

2. Masalah yang Ditemukan dalam Implementasi
Dalam proses pengerjaan, terdapat beberapa tantangan:

Manajemen ID: Karena kita tidak menggunakan database relasional (masih menggunakan ArrayList), memastikan keunikan ID saat pembuatan produk sangat penting agar fitur Edit dan Delete tidak salah sasaran.

Redirect Path: Sempat muncul potensi error ERR_TOO_MANY_REDIRECTS jika penulisan path pada instruksi return "redirect:..." tidak tepat atau relatif terhadap struktur URL yang sedang diakses. Solusinya adalah menggunakan absolute path seperti redirect:/product/list.

3. Apakah Kode Sudah Mengikuti Prinsip Secure Coding?
Secara umum, implementasi saat ini sudah mulai menerapkan dasar keamanan, namun masih banyak ruang untuk peningkatan:

Post-Redirect-Get (PRG): Saya telah menerapkan pola ini. Setelah melakukan POST (Create/Edit), sistem melakukan redirect ke halaman list. Ini mencegah pengiriman data ganda jika pengguna menekan tombol refresh pada browser.

Hidden Fields: Pada form Edit, ID produk disimpan dalam <input type="hidden">. Ini memastikan ID tetap terjaga selama proses pengiriman data tanpa harus ditampilkan kepada pengguna.

Kekurangan (Ruang Improvisasi): Kode saat ini belum memiliki validasi input yang kuat (misalnya, mencegah kuantitas negatif atau nama kosong). Selain itu, tidak ada pengecekan apakah ID yang akan dihapus benar-benar ada, yang bisa menyebabkan NullPointerException jika tidak ditangani.
