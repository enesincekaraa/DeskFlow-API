# 📘 DeskFlow Microservice Projesi

## 🧠 Proje Amacı

DeskFlow, mikroservis mimarisiyle inşa edilen modern ve güvenli bir kullanıcı yönetim sistemidir.  
Bu proje; **kayıt**, **giriş**, **e-posta bildirimi**, **JWT ile güvenlik**, **servisler arası iletişim (Feign)** ve **merkezi konfigürasyon** gibi temel mikroservis prensiplerini öğretmek ve uygulamak amacıyla geliştirilmiştir.

---

## 🏗️ Mimaride Yer Alan Servisler

### ✅ 1. `auth-service`
- Kullanıcı kayıt (`/register`) ve giriş (`/login`) işlemlerini gerçekleştirir.
- Giriş ve kayıt sonrası **JWT token** üretir.
- `user-service` üzerinden kullanıcı kaydı yapar.
- `notification-service` ile kullanıcıya e-posta gönderir.

### ✅ 2. `user-service`
- Kullanıcıları veritabanında tutar (MySQL).
- `Feign` ile diğer servislerden gelen istekleri işler.
- Kullanıcı bilgileri `DTO` + `record` yapısıyla yönetilir.
- Gelişmiş `Exception Handling` ve `Validation` yapısı vardır.
- JWT token doğrulaması ile güvenli hale getirilmiştir.

### ✅ 3. `notification-service`
- Kullanıcılara e-posta gönderimi yapar (SMTP → Gmail).
- `Feign Client` ile `auth-service` veya `user-service`'ten gelen bildirimleri işler.
- Mail ayarları `Config Server` üzerinden yönetilir.

### ✅ 4. `config-server`
- Tüm servislerin yapılandırmalarını merkezi olarak sağlar.
- GitHub'daki `config-repo` klasöründen `*.yml` dosyaları çeker.

### ✅ 5. `discovery-server` (Eureka)
- Tüm servislerin birbirini keşfetmesini sağlar.
- `Feign`, `Load Balancer`, ve `Service Discovery` işlemleri için zorunludur.

---

## 🔐 Güvenlik Mimarisi

- JWT tabanlı güvenlik yapılandırması `auth-service` içerisinde tamamlandı.
- Token doğrulaması `user-service` tarafında da gerçekleştiriliyor.
- `SecurityFilterChain` ile hangi endpointlerin açık/korumalı olduğu ayarlanabiliyor.
- Gizli bilgiler `.yml` dosyalarında `config-server` ile environment değişkeni olarak tanımlanır.

---

## 🛠️ Teknolojiler ve Araçlar

| Teknoloji         | Kullanım Alanı                           |
|------------------|------------------------------------------|
| Spring Boot 3.x  | Mikroservis uygulamaları                 |
| Spring Cloud     | Config Server, Eureka Discovery          |
| Spring Security  | JWT tabanlı kimlik doğrulama             |
| Feign Client     | Servisler arası HTTP iletişimi           |
| MySQL            | Kullanıcı verileri                       |
| Jakarta Mail     | E-posta gönderimi                        |
| Postman          | API testleri                             |
| GitHub           | Merkezi yapılandırma & proje kontrolü    |

---

## 🧪 Nasıl Test Edilir?

> 1. `config-repo` klasörünü GitHub’a yükleyin.  
> 2. `config-server` → `discovery-server` → diğer servisleri başlatın.  
> 3. `auth-service` üzerinden:
```
POST /api/v1/auth/register
POST /api/v1/auth/login
```
> 4. Token'ı alıp `user-service` endpointlerine erişin.  
> 5. Giriş/kayıt sonrası e-posta geldiğini `notification-service` loglarından doğrulayın.

---

## 📌 Şu Ana Kadar Yapılanlar

| Aşama                          | Durum      |
|-------------------------------|------------|
| Merkezi konfigürasyon (Config) | ✅ Tamamlandı  
| Service Discovery (Eureka)     | ✅ Tamamlandı  
| User kayıt/giriş               | ✅ Auth-service'e taşındı  
| JWT üretimi ve doğrulama       | ✅ Tamamlandı  
| Mail gönderimi                 | ✅ Login/Register sonrası  
| User-service güvenliği         | ✅ Token doğrulama yapısı eklendi  

---

## 🔮 Sıradaki Geliştirmeler

- ✅ Auth-service → Login & Register tamamlandı
- ✅ Notification entegrasyonu yapıldı
- 🔜 **Account-Service**: Kullanıcıya ait hesap bilgileri
- 🔜 **Transaction-Service**: Para transferi, işlem geçmişi
- 🔜 **Audit/Logger-Service**: Olayları kayıt altına alma
- 🔜 Docker-Compose ile tüm servislerin tek komutla ayağa kaldırılması
- 🔜 Frontend (Angular/React) ile bağlantı (opsiyonel)

---

## 🧑‍💻 Katkıda Bulunmak İsteyenlere

- Her servisin içinde `README.md` eklenmiştir.
- Kodlar temiz ve açıklayıcı şekilde yazılmıştır.
- Geliştiricilerin yalnızca `auth-service` ile login olup, diğer servisleri test etmesi yeterlidir.
- PR'lar, yorumlar ve katkılar memnuniyetle karşılanır 💬

---

Hazırlayan:  
**Enes İncekara**  
📧 enesincekara61@gmail.com  
🔗 [LinkedIn](https://www.linkedin.com/in/enes-incekara) | [GitHub](https://github.com/enesincekaraa)
