//Test için oluşturuldu.

public class Box
{
    public Series s1 = new Series(34);
    private Series s2 = new Series("Emir", 3455, false, 'W', 45.4, 789.6F);
    
    Box()
    {
        s1.setFriend(s2);
    }
}