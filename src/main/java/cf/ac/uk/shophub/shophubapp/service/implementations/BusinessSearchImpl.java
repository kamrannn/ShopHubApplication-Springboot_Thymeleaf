package cf.ac.uk.shophub.shophubapp.service.implementations;

import cf.ac.uk.shophub.shophubapp.data.interfaces.SHRepository;
import cf.ac.uk.shophub.shophubapp.service.BusinessSearch;
import cf.ac.uk.shophub.shophubapp.service.dto.BusinessDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BusinessSearchImpl implements BusinessSearch {

    private SHRepository shRepository;

    BusinessSearchImpl(SHRepository aRepo) {shRepository = aRepo;}

    @Override
    public List<BusinessDTO> findAllBiz() {
        return shRepository
                .findAllBiz()
                .stream()
                .map(b -> new BusinessDTO(b))
                .collect(Collectors.toList());
    }

    @Override
    public List<BusinessDTO> findSubbedBiz(String username) {
        return shRepository
                .findSubbedBiz(username)
                .stream()
                .map(b -> new BusinessDTO(b))
                .collect(Collectors.toList());
    }

    @Override
    public void subToBiz(String username, int id) {
        shRepository.subToBiz(username, id);
    }

    @Override
    public void insertBiz(BusinessDTO businessDTO) {
        shRepository.insertBiz(businessDTO.toBusiness());
    }

    @Override
    public void deleteBiz(int bizID) { shRepository.deleteBiz(bizID);}

    @Override
    public void insertUser(String username, String encodedPassword, String email) {
        if (shRepository.areCredentialsUnique(username,email)) {
            shRepository.insertUser(username, encodedPassword, email);
        }
    }


}
