package service;

import domain.MockData;
import org.apache.commons.io.FileUtils;

import javax.ejb.Stateless;
import javax.inject.Named;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Named
@Stateless
public class ReaderFileServiceImpl implements ReaderFileService{

    @Override
    public List<MockData> getListDataMock() {

        try {

            List<String> lines = FileUtils.readLines(new File( getClass().getClassLoader().getResource("MOCK_DATA.csv").getFile()));

            // lines of header
            lines  = lines.stream().skip(1).collect(Collectors.toList());

            return lines.stream().map(l -> {
                 String[] valor = l.split(",");
                 MockData dto=new MockData();
                 dto.setId(Long.valueOf((valor[0])));
                 dto.setFirstName(valor[1]);
                 dto.setLastName(valor[2]);
                 dto.setEmail(valor[3]);
                 dto.setGender(valor[4]);
                 dto.setIpAddress(valor[5]);

                 return dto;
            })
            .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
         return null;
    }
}
