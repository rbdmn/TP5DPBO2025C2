![image](https://github.com/user-attachments/assets/4f8fa77f-46fc-4b37-9725-ea2094c04b95)# JANJI
 Saya Abdurrahman Rauf Budiman dengan NIM 2301102 mengerjakan Tugas Praktikum 5 dalam mata kuliah Desain dan Pemrograman Berorientasi Objek untuk keberkahanNya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.

# Desain Program
Tugas Praktikum 5 ini mengimplementasikan bagaimana kita bisa mengembangkan project yang sudah ada menjadi lebih baik lagi dan terstruktur. Disini kita disuruh untuk melengkapkan program GUI yang sebelumnya telah dibuat pada TP4 serta membuat berbagai prompt/dialog untuk error handling dan integrasikan ke database. Spesifikasinya ialah:
- Hubungkan semua proses CRUD dengan database.
- Tampilkan dialog/prompt error jika masih ada input yang kosong saat insert/update.
- Tampilkan dialog/prompt error jika sudah ada NIM yang sama saat insert.

Maka dari itu disini saya akan melakukan spesifikasi tersebut dan modifikasi program GUI yang sudah ada pada TP4.

### Yang di Modifikasi
1) Pertama tama saya mau melengkapi CRUD dulu dengan mengintegrasikan update dan delete (insert sudah dilakukan pada praktikum).

Untuk bagian Update saya hanya menambah sql query yang langsung menghubungkan ke database dengan isi querynya yaitu di gambar berikut
![image](https://github.com/user-attachments/assets/57b5f9c2-2195-47d3-9e32-b9f4ef07ff61)
Penjelasan: Update dari tabel mahasiswa dan ubah (set) nim baru, nama, jenis kelamin, dan prodi dimana nim nya itu sama dengan nim lamanya. Maksud dari nim baru ialah nim hasil update-an dan maksud dari nim lama yaitu yang sebelum terupdate. 

Untuk bagian Delete juga saya hanya menambah sql query dengan isi querynya di gambar berikut
![image](https://github.com/user-attachments/assets/466c3234-52a6-46a4-b92c-0a69f22995da)
Penjelasan: Delete dari tabel mahasiswa dimana nim nya itu yang telah di select.

2) Kedua yaitu untuk membuat dialog/prompt error kalau ada inputan yang kosong atau tidak terisi saat insert ataupun update, berikut fungsi prosedur yang telah saya buat:
![image](https://github.com/user-attachments/assets/1208c3b0-820d-401c-a479-6a7c2810a605)
Penjelasan: Jadi saya membuat suatu fungsi boolean yang bisa membantu mengecek inputan ini terverifikasi valid atau tidak. Dengan didalamnya ada berbagai kondisi seperti apakah field nimnya kosong, field namanya kosong, combobox jenis kelamin kosong, serta dengan radio button pada prodinya kosong. Jika salah satu dari berbagai kondisi itu merupakan benar, maka akan dimunculkan pesan dialog error dan akan mengreturn false yang artinya proses tidak bisa dieksekusi. Prosedur `CekInputValid` ini tentunya ada di bagian logika insert dan logika update.

3) Ketiga yaitu untuk membuat dialog/prompt error kalau ada NIM yang sama saat insert dan update. Bedanya dengan dialog/prompt error sebelumnya, disini akan pakai sql query. Berikut fungsi prosedur yang telah saya buat:
![image](https://github.com/user-attachments/assets/98798c48-34c7-4e5f-a3b7-8ecbef6a01e1)
Penjelasan: Di dalam query itu artinya mencari ke seluruh data pada tabel mahasiswa dimana nim nya ada pada tabel itu. Disini juga menggunakan `ResultSet` untuk membantu pemroresan sql. Dan jika NIM-nya sudah ada maka dari itu akan mengreturn false dan memberhentikan eksekusinya (isi pesan dialognya ada di logika fungsi insert dan update).

# Alur Program
Pertama tama saat di run dimulai dengan form kosong dan isi tabel yang sudah ada. Lalu user bisa menambahkan data baru, memilih baris tabel untuk diedit datanya, dan menghapus salah satu baris. 
- Kalau tombol add diklik, akan divalidasi dulu (input kosong atau NIM duplikat). Jika lolos data tersebut akan disimpan ke database.
- Berlaku juga dengan Update diklik, akan divalidasikan terlebih dahulu. Jika lolos data akan terupdate dan diperbarui ke database.
- Dan jika tombol Delete diklik, datanya akan langsung terhapus di database

# Rekaman Penjelasan Program
https://github.com/user-attachments/assets/9f189a35-346f-4607-bd20-7c22296eddac

