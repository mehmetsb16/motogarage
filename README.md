🏍️ Motogarage: Garaj Yönetim ve Bakım Takip Sistemi
Motogarage, bir garajdaki araç envanterini yönetmek ve bakım geçmişlerini izlemek için geliştirilmiş Spring Boot tabanlı bir backend projesidir. Profesyonel mimari standartları (DTO, Exception Handling, RDBMS İlişkileri) gözetilerek tasarlanmıştır.

🚀 Proje Hakkında
Bu proje, araçların veritabanı düzeyinde yönetilmesini ve her araca özel bakım kayıtlarının tutulmasını sağlar. Vehicle ve Maintenance varlıkları arasında kurulan ilişkisel yapı ile tam bir envanter yönetimi sunar.

🛠️ Teknik Özellikler
Backend: Java 17, Spring Boot, Spring Data JPA

Veritabanı: H2 Database (In-Memory)

Güvenlik: Spring Security (Basic Auth)

Mimari: Katmanlı Mimari (Entity, Controller, Service, Repository, DTO)

İşlemler: Full CRUD (Create, Read, Update, Delete) desteği

⚙️ API Uç Noktaları (Endpoints)
Araç Yönetimi (Vehicle)
GET /api/vehicles - Tüm araçları listeler.

POST /api/vehicles - Yeni araç ekler.

PUT /api/vehicles/{id} - Mevcut araç bilgilerini günceller.

DELETE /api/vehicles/{id} - Garajdan araç siler.

Servis/Bakım Yönetimi (Maintenance)
GET /maintenances - Tüm bakım kayıtlarını listeler.

GET /maintenances/{id} - Belirli bir bakım kaydını getirir.

GET /maintenances/vehicle/{vehicleId} - Belirli bir aracın tüm bakım geçmişini getirir.

POST /maintenances - Yeni bakım kaydı oluşturur.

PUT /maintenances/{id} - Bakım kaydını günceller.

DELETE /maintenances/{id} - Bakım kaydını siler.

📊 Veritabanı İlişkisi
Sistemde araçlar ve bakımlar arasında Many-to-One ilişki kullanılarak, her bakım kaydı mutlaka bir araca atanır.

🔐 Erişim Bilgileri
Admin: admin / admin123

Kullanıcı: user / 1234

📝 Kurulum
Projeyi klonlayın: git clone <repo-url>

IntelliJ IDEA üzerinde projenizi açın.

MotogarageApplication sınıfını çalıştırın.

Postman kullanarak http://localhost:8080 üzerinden isteklerinizi yönetin.
