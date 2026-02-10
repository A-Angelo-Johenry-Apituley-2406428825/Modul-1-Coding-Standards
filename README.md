# Modul-1-Coding-Standards

# Reflection 1
1. Prinsip Clean Code yang Diterapkan
- Meaningful Names: Saya menggunakan penamaan variabel dan metode yang deskriptif. Contohnya, findAll(), findById(), update(), dan delete() pada bagian Service dan Repository memudahkan pemahaman tujuan dari setiap fungsi tanpa memerlukan komentar.

- Separation of Concerns: Struktur proyek memisahkan tanggung jawab dengan jelas menggunakan pola MVC. Controller menangani HTTP request, Service menangani logika bisnis, dan Repository fokus pada manajemen data. Hal ini membuat kode lebih mudah diuji dan dipelihara.

- Don't Repeat Yourself: Penggunaan library Lombok (seperti @Getter dan @Setter) membantu menghilangkan kode yang berulang. Selain itu, logika manipulasi list dipusatkan di Repository sehingga tidak ada duplikasi logika di layer Service.

2. Masalah yang Ditemukan dalam Implementasi
Conflict saat merge branch edit-product dan delete-product ke branch main

Solusi:
- Membuka file yang berkonflik di IntelliJ, kemudian memilih secara manual bagian kode mana yang ingin dipertahankan (Accept Incoming Change, Accept Current Change, atau menggabungkan keduanya).
- Setelah konflik diselesaikan secara manual, saya melakukan git add pada file tersebut untuk menandai bahwa konflik telah selesai, kemudian melakukan git commit untuk mengakhiri proses merge.

3. Secure Coding yang diterapkan
- Pada form Edit, ID produk disimpan dalam <input type="hidden">. Ini memastikan ID tetap terjaga selama proses pengiriman data tanpa harus ditampilkan kepada pengguna.
- Penggunaan CDN dengan atribut Subresource Integrity untuk memastikan bahwa library eksternal tidak dimodifikasi oleh pihak ketiga yang tidak bertanggung jawab.

# Reflection 2

1.
Setelah menulis unit test, saya merasa lebih percaya diri terhadap integritas kode saya. 

Berapa banyak unit test yang harus dibuat dalam satu class? Tidak ada angka pasti, namun jumlahnya harus cukup untuk mencakup semua jalur logika (branch coverage).

Apakah 100% Code Coverage berarti kode bebas bug? Tidak, karena Code coverage hanyalah menunjukkan baris kode mana yang telah dieksekusi selama tes. Coverage tinggi tidak menjamin logika kodenya benar. Contohnya, kita bisa memiliki coverage 100% pada fungsi pembagian, tetapi jika kita lupa mengetes pembagian dengan angka nol, maka program bisa saja crash.

2.
Jika saya membuat functional test baru yang memverifikasi jumlah item di product list, dengan menggunakan prosedur setup dan variabel dari functional test yang sudah dibuat sebelumnya, ada beberapa isu Clean Code yang muncul:

Don't Repeat Yourself: Kode untuk inisialisasi baseUrl, pengambilan serverPort, dan konfigurasi driver akan berulang di setiap file test baru. Jika ada perubahan pada konfigurasi server atau driver, saya harus mengubah semua file test satu per satu.

Lack of Abstraction: Tes fungsional menjadi terlalu detail dalam hal teknis infrastruktur (seperti pengaturan URL) daripada fokus pada skenario perilaku pengguna
