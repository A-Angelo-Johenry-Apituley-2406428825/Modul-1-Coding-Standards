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

# Reflection 3
1. Code quality issues yang saya fix sebagian besar berpengaruh terhadap maintainability dan reliability, yaitu:
   - Isu field injection
     Strategi: menghapush field injection lalu menggantinya dengan constructor injection
   - Isu method kosong
     Strategi: menambahkan nested comment yang menjelaskan mengapa method tersebut kosong
   - Mengelompokkan dependencies berdasarkan fungsinya
   - Menghapus import yang tidak dipakai
   - Menghapus exception yang tidak terpakai
   - Mengganti "read-all" menjadi "contents:read"
3. Menurut saya, implementasi yang telah saya buat sudah memenuhi definisi Continuous Integration (CI) dan Continuous Deployment (CD) dengan alasan sebagai berikut:
  - Aspek Continuous Integration terpenuhi karena setiap kali ada perubahan kode yang di-push ke repositori, GitHub Actions secara otomatis menjalankan test suite dan analisis kualitas kode (SonarCloud) untuk memastikan integrasi baru tidak merusak fitur yang ada. 
  - Aspek Continuous Deployment tercapai karena alur kerja (workflow) yang kamu buat memungkinkan aplikasi dideploy secara otomatis ke PaaS (Heroku) segera setelah kode dinyatakan lulus tahap pengujian dan analisis.

# Reflection 4
1. Principle yang saya terapkan yaitu:
    1. Single Responsibility Principle
        - Memindahkan logika update dari CarRepository.java ke Car.java, sehingga CarRepository hanya bertugas untuk mengelola akses data.
        - Memisahkan CarController 
    2. Open Closed Principle
        - Abstraction melalui interface sehingga jika ingin mengganti penyimpanan data, cukup membuat implementasi baru tanpa mengubah controller.
    3. Liskov Substitution Principle
        - Menghapus 'extends ProductController' karena CarController bukan turunan dari ProductController sehingga setiap controller berdiri sendiri sesuai tanggung jawabnya.
    4. Interface Segregation Principle
        - Interface dari CarService tidak besar sehingga tidak dilakukan perubahan
    5. Dependency Inversion Principle
        - Mengganti private CarServiceImpl carService, menjadi private CarService carService, sehingga Controller bergantung pada interface, bukan concrete class
2. Kelebihan menerapkan SOLID, yaitu:
    - Kode lebih mudah dimaintain
        Karena setiap class memiliki tanggung jawab jelas (SRP), perubahan pada repository tidak akan mempengaruhi controller 
        Contohnya, jika ingin mengganti UUID menjadi auto-increment ID, hanya perlu mengubah CarRepository, tanpa mengubah controller
    - Kode menjadi scalable
        Karena menggunakan interface (OCP & DIP)
        Jika ingin mengganti penyimpanan ke database:
          public class CarServiceDatabaseImpl implements CarService
        Controller tidak perlu diubah.
    - Lebih mudah dilakukan test
        Karena controller bergantung pada interface, sehingga dapat dilakukan mocking, yang dapat membuat unit testing menjadi lebih mudah dan terisolasi.
    - Reduced coupling
        Controller tidak tahu bagaimana data disimpan, ia hanya tahu bahwa ada CarService.
3. Jika tidak menerapkan SOLID, maka:
    - Tight coupling
        Jika controller menggunakan CarServiceImpl, maka jika implementasi berubah, maka controller harus ikut diubah.
    - Code duplication
        Jika CarController meng-extend ProductController, maka method yang tidak relevan bisa ikut diwariskan.
    - Lebih sulit ditest
        Controller tidak dapat dimock dengan mudah.
    - Sulit diextend
        Jika ingin mengganti storage ke database, kita harus memodifikasi class yang sudah ada.

# Reflection Module 4
1. 
Dalam exercise ini saya menerapkan metode Test-Driven Development (TDD) dengan alur RED, GREEN, dan REFACTOR. Menurut saya, pendekatan ini cukup membantu dalam proses pengembangan fitur karena test dibuat terlebih dahulu sebelum implementasi kode. Dengan cara ini saya dapat memahami dengan lebih jelas perilaku yang diharapkan dari sistem sebelum menulis kode program.

Pada tahap RED, saya menuliskan test yang menggambarkan fungsi yang diinginkan. Pada tahap GREEN, saya mengimplementasikan kode seminimal mungkin agar test dapat berhasil dijalankan. Setelah semua test berhasil, pada tahap REFACTOR saya memperbaiki struktur kode agar lebih rapi dan mudah dibaca tanpa mengubah perilaku program.

Namun, dalam praktiknya terdapat beberapa kesulitan, terutama ketika harus membuat test terlebih dahulu tanpa implementasi yang sudah jelas. Selain itu, beberapa objek seperti Order memiliki validasi tertentu sehingga data untuk test harus disiapkan dengan benar agar tidak menimbulkan error. Ke depannya, saya perlu merancang skenario test lebih awal dan membuat helper method untuk mempermudah pembuatan objek yang sering digunakan dalam test sehingga kode test menjadi lebih sederhana dan mudah dipelihara.
2. 
Unit test yang dibuat pada exercise ini sebagian besar telah mengikuti prinsip F.I.R.S.T. yaitu Fast, Independent, Repeatable, Self-validating, dan Timely. Test yang dibuat bersifat fast karena hanya menguji unit kecil dari program seperti model, repository, dan service tanpa melibatkan sistem eksternal. Test juga independent karena setiap test dijalankan secara terpisah dan menggunakan @BeforeEach untuk memastikan kondisi awal yang sama.

Selain itu, test bersifat repeatable karena dapat dijalankan berkali-kali dengan hasil yang konsisten. Test juga self-validating karena menggunakan assertion seperti assertEquals dan assertNull untuk menentukan keberhasilan test secara otomatis. Terakhir, test bersifat timely karena dibuat sebelum atau selama proses implementasi kode sesuai dengan pendekatan TDD.

Meskipun demikian, masih terdapat beberapa hal yang dapat diperbaiki, seperti mengurangi duplikasi kode pada bagian setup test. Dengan membuat helper method, struktur test dapat menjadi lebih rapi dan lebih mudah dipahami.
