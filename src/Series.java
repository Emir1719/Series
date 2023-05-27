/**
 * Seriler, istediğiniz sayıda ve her çeşit veri türünde değerler alır.
 * Yapısında çift yönlü bağlı liste kullanılmıştır. Seriler, dizilerden farklı
 * olarak istediğiz kadar değer girmenizi sağlar. Ayrıca serinizi hem stack hem de queue
 * mantığında kullanabilirsiniz.
 * @author Emir Öztürk
 * @version 1.0
 */
public class Series
{
    private Node head;
    private Node tail;
    private Series friend;

    Series()
    {
        this.head = null;
        this.tail = null;
        //ID, baştan başlatılır:
        Node.setCount(-1);
    }

    /**
     * Herhangi bir fonksiyon kullanmadan seriye istediğiniz kadar veri girmenizi sağlar.
     * Mevcut bir Object dizideki elemanları seriye çevirebilirsiniz.
     * @param values bir ya da daha fazla değer
     */
    Series(Object... values)
    {
        this.head = null;
        this.tail = null;
        Node.setCount(-1);
        addAll(values);
    }

    //#region get, update. find functions

    /**
     * Örnek Kullanım:
     * <pre>{@code
     *Series s = new Series(true,"See",44,'F',44);
     *s.get(0); //true
     * }</pre>
     * @param i serinin ID değeri
     * @return Serinin i. konumundaki verisini döndürür
    */
    public Object get(int i)
    {
        if (this.length() != 0 && i < this.length() && i >= 0) {
            Node temp = this.find(i);
            return temp.getData();
        }
        return null;
    }

    /**
     * Örnek Kullanım:
     * <pre>{@code
     *Series s = new Series(true,"See",44,'F',44);
     *s.getAsInt(0); //true
     * }</pre>
     * @param i serinin ID değeri
     * @return Serinin i. konumundaki verisini <strong>int</strong> olarak döndürür
    */
    /*public int getAsInt(int i)
    {
        if (this.length() != 0 && i < this.length() && i >= 0) {
            Node temp = this.find(i);
            return (int)temp.getData();
        }
        return 0;
    }*/
    
    /**
     * Örnek Kullanım:
     * <pre>{@code
     *public Series s1 = new Series();
     *private Series s2 = new Series(true,"love",'A');
     *s1.setFriend(s2);
     *s1.getFriend(2); //A
     * }</pre>
     * @param i serinin ID değeri
     * @return Serinin arkadaşının i. konumundaki verisini döndürür.
     * Hata sonucunda (serinin arkadaşı yoksa) null döndürür.
    */
    public Object getFriend(int i)
    {
        if (this.friend != null && i < this.friend.length() && i >= 0)
        {
            Node temp = this.friend.getHead();
            while (temp != null)
            {
                if (temp.getID() == i) break;
                temp = temp.getNext();
            }
            return temp.getData();
        }
        return null;
    }

    /**
     * Seriyi ID'sine göre bulup adresini döndürür.
     * @param id ID
     * @return Node (adresi)
     */
    private Node find(int id)
    {
        if (this.length() != 0 && id < this.length() && id >= 0)
        {
            Node temp = this.head;
            while (temp != null)
            {
                if (temp.getID() == id) break;
                temp = temp.getNext();
            }
            return temp;
        }
        return null;
    }

    /**
     * Serinin i. konumundaki verisini yeni değerle değiştir.
     * @param i değiştirmek istediğiniz verinin ID'si.
     * @param newValue atamak istediğiniz yeni değer.
     * @return 0: Başarısız ve 1: Başarılı
     */
    public int update(int i, Object newValue)
    {
        if (this.length() != 0 && i < this.length() && i >= 0)
        {
            Node temp = find(i);
            temp.setData(newValue);
            return 1;
        }
        return 0;
    }

    /**
     * Belirtilen ID ve sonrasını günceller.
     * @param startID ID'nin güncellenmeye nereden başlayacağını belirtir.
     */
    private void updateID(int startID)
    {
        int newID = startID;
        Node temp = this.find(startID);
        //İlk eleman silindiğinde bu koşul gerçekleşecek:
        if (temp == null)
            temp = this.find(startID+1);

        while (temp != null)
        {
            temp.setID(newID);
            newID++;
            temp = temp.getNext();
        }
    }

    //#endregion

    //#region add functions

    /**
     * Serinin ilk elemanına veri ekler.
     * @param value seriye eklenecek değer
     */
    public void addHead(Object value)
    {
        Node newNode = new Node(value);
        if (this.head == null) {
            this.head = newNode;
            this.tail = newNode;
        }
        else {
            this.head.setBefore(newNode);
            newNode.setNext(this.head);
            this.head = newNode;
            this.head.setID(0);
            this.updateID(0);
        }
    }

    /**
     * Serinin sonuna ekleme yapar.
     * Eğer seri boşsa veriyi başa ekleyip ilk eleman yapar.
     * @param value seriye eklenecek değer
     */
    public void add(Object value)
    {
        if (this.head == null) {
            //Başa ekleme:
            this.addHead(value);
        }
        else
        {
            //Sona ekleme:
            //Count, static olduğundan dolayı yenilenmek zorunda:
            Node.setCount(this.tail.getID());
            Node newNode = new Node(value);
            Node temp = this.head;
            while(temp.getNext() != null)
            {
                temp = temp.getNext();
            }
            temp.setNext(newNode);
            newNode.setBefore(temp);
            this.tail = newNode;
        }
        /*
            Algoritma Açıklaması:
            1) Eğer liste boş ise yeni düğüm başa eklenir.
            2) Eğer liste boş değilse temp'i ilk elemana konumlandır ve son elemana gelene kadar ilerlet.
            3) Temp son elemana geldiğinde yeni düğüm son elemandan sonra geleceği için temp'in next'i yeni düğüm olacak.
            4) Son eleman da yeni düğümden önce geleceği için yeni düğümün öncesi temp (son eleman) olur.
        */
    }

    /**
     * Seriye istediğiniz kadar veri girmenizi sağlar.
     * Mevcut bir Object dizideki elemanları <code>addAll</code> fonksiyonu ile
     * bir seriye çevirebilirsiniz.
     * @param values bir ya da daha fazla değer
     */
    public void addAll(Object... values)
    {
        for (Object i : values)
            this.add(i);
    }

    public void addAmong(int id, Object... values)
    {
        for (Object i : values)
        {
            if (this.head == null || id == 0) addHead(i);
            else if (id >= length()) add(i);

            else
            {
                Node newNode = new Node(i);
                Node temp = find(id - 1);
                newNode.setNext(temp.getNext());
                temp.getNext().setBefore(newNode);
                newNode.setBefore(temp);
                temp.setNext(newNode);
                newNode.setID(id);
                this.updateID(id);
                //Birden fazla değer girilirse id artmalı:
                id++;
                /*
                  Algoritma Açıklaması (else kısmı)
                  1) temp, yeni düğümün ekleneceği yerin bir öncesine konumlandı.
                */
            }
        }
    }

    /**
     * İstediğiniz değer arasındaki sayıları seriye ekler.
     * Sadece int sayı eklemesi yapar.
     * @param firstValue başlangıç değeri (dahil)
     * @param finalValue bitiş değeri (dahil)
     * @param chance değişim miktarı (sayı pozitif ise artırır, negafif ise azaltır.)
     */
    public void addRange(int firstValue, int finalValue, int chance)
    {
        //change=0 olduğunda sonsuz döndüye gireceğinden dolayı engellenir:
        if (chance == 0) return;

        if (chance > 0) {
            //artırma işlemi:
            for (int i = firstValue; i <= finalValue; i+=chance) {
                this.add(i);
            }
        }
        else
        {
            //for'un içindeki i+=chance ifadeden dolayı chance negatif olmalıdır
            if (chance > 0) return;

            //azaltma işlemi:
            for (int i = firstValue; i >= finalValue; i+=chance) {
                this.add(i);
            }
        }
    }

    //#endregion

    //#region delete functions

    //destroy eklenebilir

    /**
     * Değeri ilk eşleşen eleman seriden silinir.
     * Yalnızca tek bir eleman silinir.
     * <hr>
     * Örnek Kullanım:
     * <pre>{@code
     *Series s = new Series(true,"See",44,'F',44);
     *deleteByValue(44);
     *s.list(); //true,"See",'F',44
     * }</pre>
     * @param values değerler
     */
    public void deleteByValue(Object... values)
    {
        if (this.head == null) return;
        for (Object value : values)
        {
            //Silinecek değer seride yoksa hatalıdır:
            if (!contains(value)) continue;

            Node temp = getHead();
            while (temp.getNext() != null)
            {
                //İlk değer eşleşen eleman gelince döngü sonlanır:
                if (temp.getData() == value) break;
                temp = temp.getNext();
            }
            //Silme işleminde ilk ve son eleman için özel koşul uygulanır:
            if (temp == this.head)
                this.head = this.head.getNext();
            else if (temp == this.tail) {
                this.tail = this.tail.getBefore();
                this.tail.setNext(null);
            }
            else {
                //Eğer aradaki elemanlar silinecekse:
                temp.getBefore().setNext(temp.getNext());
                temp.getNext().setBefore(temp.getBefore());
            }
            this.updateID(temp.getID());
        }
    }

    /**
     * ID'ye göre eleman seriden silinir.
     * Yalnızca tek bir eleman silinir.
     * <hr>
     * Örnek Kullanım:
     * <pre>{@code
     *Series s = new Series(12,"A",34,3.5,12);
     *deleteByID(1);
     *s.list(); //12,34,3.5,12
     * }</pre>
     * @param id serinin ID'si
     */
    public void deleteByID(int id)
    {
        if (this.head == null) return;
        if (id >= length() || id < 0) return;
        
        Node temp = this.find(id);

        //Silme işleminde son ve ilk eleman için özel silme uygulanır:
        if (temp == this.head)
            this.head = this.head.getNext();
        else if (temp == this.tail) {
            this.tail = this.tail.getBefore();
            this.tail.setNext(null);
        }
        else {
            //Eğer aradaki elemanlar silinecekse:
            temp.getBefore().setNext(temp.getNext());
            temp.getNext().setBefore(temp.getBefore());
        }
        
        this.updateID(temp.getID());
    }

    /**
     * Girilen değere sahip bütün elemanları siler.
     * <hr>
     * Örnek Kullanım:
     * <pre>{@code
     *Series s = new Series(12,"A",34,3.5,12);
     *deleteAllByValue(12,3.5);
     *s.list(); //A,34
     * }</pre>
     * @param values değerler
     */
    public void deleteAllByValue(Object... values)
    {
        for (Object value : values)
        {
            if (this.head == null) return;

            Node temp = getHead();
            while (temp != null)
            {
                if (temp.getData() == value)
                {
                    this.deleteByValue(value);
                }
                temp = temp.getNext();
            }
        }
    }

    //#endregion

    //#region list, length functions

    /**
     * Serinizin içindeki verileri listeler.
     * <hr>
     * Örnek Kullanım:
     * <pre>{@code
     *Series s = new Series(25,"A",true);
     *s.list();
     * }</pre>
     * Çıktı:
     * <pre>
     *ID(0): 25
     *ID(1): A
     *ID(2): true
     * </pre>
     */
    public void list()
    {
        if (this.head != null)
        {
            Node temp = this.head;
            while(temp != null)
            {
                System.out.println("ID(" + temp.getID() + "): " + temp.getData());
                temp = temp.getNext();
            }
        }
        else System.out.println("Seride hiçbir veri bulunmuyor.");
    }

    /**
     * Serinizin arkadaşının içindeki verileri listeler.
     * <hr>
     * Örnek Kullanım:
     * <pre>{@code
     *Series s = new Series();
     *Series f = new Series(1, 56.7);
     *s.setFriend(f);
     *s.listFriend();
     * }</pre>
     * Çıktı:
     * <pre>
     *ID(0): 1
     *ID(1): 56.7
     * </pre>
     */
    public void listFriend()
    {
        if (this.friend != null && this.friend.head != null )
        {
            Node temp = this.friend.head;
            while(temp != null)
            {
                System.out.println("ID(" + temp.getID() + "): " + temp.getData());
                temp = temp.getNext();
            }
        }
        else System.out.println("Seride hiçbir veri bulunmuyor ya da serinin arkadaşı yok.");
    }

    public void listDistinct()
    {
        if (this.head != null)
        {
            //temp, ilk eleman olur ve seriye eklenir.
            Node temp = getHead();
            Series series = new Series(temp.getData());
            while(temp != null)
            {
                //Eğer eklenecek değer serinin içinde yoksa eklenir, varsa eklenmez:
                if (!series.contains(temp.getData()))
                    series.add(temp.getData());

                temp = temp.getNext();
            }
            //Sadece eşsiz değerler eklendiği için onlar listelenecek:
            series.list();
        }
        else System.out.println("Seride hiçbir veri bulunmuyor.");
    }

    public int length() {
        //return tail.getID() + 1;
        return Node.getCount() + 1;
    }

    //#endregion
    
    //#region bool functions

    public boolean contains(Object value)
    {
        if (this.head == null) {
            return false;
        }
        Node temp = this.head;
        while (temp != null) 
        {
            if (temp.getData() == value) {
                return true;
            }
            temp = temp.getNext();
        }
        return false;
    }

    public boolean isEmpty()
    {
        if (this.head == null) return true;
        else return false;
    }

    //#endregion

    //#region stack functions

    /**
     * Son elemanı döndürür ve ardından siler.
     * @return son elemanın verisi
     */
    public Object pop()
    {
        if (isEmpty()) return null;

        Node temp = this.tail;
        this.deleteByID(this.tail.getID());
        return temp.getData();
    }

    /**
     * Son elemanı döndürür fakat silmez.
     * @return son elemanın verisi
     */
    public Object peek()
    {
        if (isEmpty()) return null;
        return this.tail.getData();
    }

    //#endregion

    //#region queue functions

    /**
     * İlk elemanı döndürür ve ardından siler.
     * @return ilk elemanın verisi
     */
    public Object poll()
    {
        if (isEmpty()) return null;

        Object data = this.head.getData();
        this.deleteByID(this.head.getID());
        return data;
    }

    /**
     * İlk elemanı döndürür fakat silmez.
     * @return ilk elemanın verisi
     */
    public Object callHead()
    {
        if (this.head != null) return head.getData();
        else return null;
    }

    //#endregion

    //#region getter and setter functions

    public Node getHead() {
        if (this.head != null) return head;
        else return null;
    }

    public void setFriend(Series friend) {
        if (this != friend) this.friend = friend;
        else System.out.println("Arkadaş seri, serinin kendisi olamaz!");
    }

    //#endregion
}