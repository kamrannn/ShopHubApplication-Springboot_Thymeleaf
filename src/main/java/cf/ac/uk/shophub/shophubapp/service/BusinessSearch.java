package cf.ac.uk.shophub.shophubapp.service;


import cf.ac.uk.shophub.shophubapp.service.dto.BusinessDTO;

import java.util.List;

public interface BusinessSearch {

    List<BusinessDTO> findAllBiz();

    List<BusinessDTO> findSubbedBiz(String username);

    void subToBiz(String username, int id);

    void insertBiz(BusinessDTO businessDTO);

    void deleteBiz(int bizID);

    void insertUser(String username, String encodedPassword, String email);
}
