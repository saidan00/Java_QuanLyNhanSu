package bus;

import dao.PhongBanDAO;
import dto.PhongBanDTO;
import java.util.ArrayList;

public class PhongBanBUS {
    private PhongBanDAO PhongBanDAO = new PhongBanDAO();
    
    public ArrayList<PhongBanDTO> PhongBanAll()
    {
        return PhongBanDAO.PhongBanAll();
    }
    
    public void PhongBanAdd(PhongBanDTO pb)
    {
        PhongBanDAO.PhongBanAdd(pb);
    }
    
    public void PhongBanEdit(PhongBanDTO pb)
    {
        PhongBanDAO.PhongBanEdit(pb);
    }
    
    public void PhongBanDelete(int maPhongBan)
    {
        PhongBanDAO.PhongBanDelete(maPhongBan);
    }
}
