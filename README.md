Geliştirme Ortamının Kurulumu

Gereksinimler
Java Development Kit (JDK) 17 veya daha yeni bir sürüm MySQL veritabanı Git

Adımlar 
Java Development Kit (JDK) Kurulumu:

JDK'nın sisteminizde yüklü olduğunu kontrol edin:
java -version JDK yüklü değilse, resmi Oracle JDK web sitesine gidin ve JDK'nın en son sürümünü indirin ve kurun: Oracle JDK

MySQL Veritabanının Kurulumu:
MySQL veritabanını sistem üzerinde kurun. İndirme ve kurulum adımları için MySQL'nin resmi web sitesine başvurun: MySQL

Git'in Kurulumu:
Git'in sisteminizde yüklü olduğunu kontrol edin:
git --version Git yüklü değilse, Git'in resmi web sitesine gidin ve uygun sürümü indirin ve kurun: Git

Projenin Klonlanması:
Bir komut satırı veya terminal açın ve projeyi klonlamak istediğiniz dizine gidin:
cd hedef_dizin Projeyi klonlayın:

git clone https://github.com/BarisYuceturk93/todo-app-api.git

Projenin Ayarlanması:
Proje dizinine gidin:

cd todoapp 

Uygulama bağımlılıklarını indirmek ve projeyi derlemek için aşağıdaki komutu çalıştırın:
./gradlew build 

Veritabanının Yapılandırılması:
application.properties dosyasını düzenleyin. Proje kök dizininde bulunmalıdır.

Aşağıdaki bağlantı bilgilerini MySQL veritabanınıza göre güncelleyin:

spring.datasource.url=jdbc:mysql://localhost/todoappdatabase
spring.datasource.username=kullanici_adi 
spring.datasource.password=sifre 
Diğer veritabanı yapılandırma ayarlarını gerektiğinde düzenleyebilirsiniz. 
(burada database ismi kullanıcı adı ve şifre sizin değerleriniz olmalıdır)

Uygulamanın Başlatılması:
Aşağıdaki komutu çalıştırarak uygulamayı başlatın:

./gradlew bootRun Uygulama başarıyla başlatıldığında, localhost:4040 url'si ile uygulama kullaılabilir durumda olacaktır.

Kullanım ve detaylar
Proje, TodoApp adlı bir görev yönetimi uygulamasını içerir. Uygulamada Spring Security ve JWT ile güvenlik ve 
yetkilendirmeler sağlanmıştır. Crud işlemleri JPA repository yardımıyla yapılmış ve MySQL 8.0 database olarak kullanılmıştır.
Sonrasında bazı temel işlemleri yapabilecek şekilde GraphQL eklenmeye çalışılmıştır. Uygulama geliştirmedeki kolaylık ve 
zaman yönetimi açısından monolitik mimaride tasarlanmış ancak mikroservise taşınmada kolaylık gözetilerek ilgili 
servis paketlerine ayrılarak yapılmıştır. Bir arayüz geliştirilmediği için endpointler üzerinden CURL ve ya POSTMAN 
gibi aracılarla kullanmak uygun olacaktır.

Uygulamanın kullanımında,

AuthController içerisindeki endpointlerden kullanıcı kaydı yapmanız ya da databasede kayıtlı bulunan bir kullanıcı girişi 
yapmanız mümkün olacaktır. Bu endpointler SecurityConfig sınıfında tüm kullanıcılara açık bırakılmıştır. Bunun yanında parola
unutulması anında kullanıcı adı ve email ile kullanıcı bilgilerini isteyen endpoint de açık bırakılmıştır. 
Kullanıcı kaydının peşine kullanıcı girişi yapmak diğer endpointlere erişimi mümkün kılacaktır. 
Bu işlem sonrasında generate edilen token AuthController, UserController veya WorkItemController uzantılarında bulunan 
endpointlere Bearer <token> yöntemiyle gönderildiği takdirde endpointlerde bulunan işlemler gerçekleştirilebilir olacaktır.

Bu işlemler arasında yeni kullanıcı eklemek(UserController), kullanıcıları listelemek(UserController),
görev oluşturmak(WorkItemController), görevi üstlenmek(UserController), görevi sonlandırmak(WorkItemController) ve 
silmek (WorkItemController), bir kullanıcıya rol/yetki atamak(AuthController) vardır. Bunun yanı sıra kullanıcılar kendi kullanıcı adları ile kendilerine bağlanan 
görevleri görebileceklerdir(UserController). Tüm Controller sınıflarında ek olarak temel CRUD işlemleri yapan endpointler mevcuttur. Bazı sorgular için
ilgili repository sınıflarında query'ler yazılmıştır. 

