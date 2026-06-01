# Motogarage Backend API

Bu proje, Yönetim Bilişim Sistemleri dersi final projesi için geliştirilmiştir. Araç bakım kayıtlarını yönetmek için profesyonel bir API sağlar.

## Kullanılan Teknolojiler
- Java
- Spring Boot
- Spring Data JPA
- Spring Security
- H2 Database

## Kurulum
1. Projeyi bilgisayarınıza klonlayın.
2. IntelliJ IDEA ile projeyi açın.
3. Spring Boot uygulamasını çalıştırın.
4. Postman uygulamasını açarak API isteklerini test edin.

## Yetkilendirme
Bu API, Spring Security RBAC yapısını kullanır:
- **USER:** `user` / `1234` (Sadece listeleme/GET işlemleri)
- **ADMIN:** `admin` / `admin123` (Ekleme/POST, Silme/DELETE, Güncelleme/PUT işlemleri)
