package repository;

import config.DataBaseInitializerBean;
import domain.MockData;
import org.apache.log4j.Logger;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@RequestScoped
public class MockDataServiceImpl implements MockRepositoryService {

    private final static Logger logger = Logger.getLogger(MockDataServiceImpl.class);
    private StringBuilder sb=new StringBuilder();

    @Inject
    private DataBaseInitializerBean dataBaseInitializerBean;


    @Override
    public void cleanTable() {
        try {
            Statement stm = dataBaseInitializerBean.getConnection().createStatement();
            stm.execute("DELETE FROM mock_data");
            logger.info("---------------------Table cleaned------------");
            stm.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveMockData(List<MockData> mockDataList) {

        try {

            Statement stm = dataBaseInitializerBean.getConnection().createStatement();

            mockDataList.stream()
                    .forEach( mockData -> {
                        try {
                             stm.execute(queryInsert(mockData));
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    });
            logger.info("---------------Data Inserted-----------------");
            stm.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private String queryInsert(MockData mockData) {
        sb.setLength(0);
        sb.append("INSERT INTO mock_data (")
                .append("id,")
                .append("first_name,")
                .append("last_name,")
                .append("email,")
                .append("gender,")
                .append("ip_address) VALUES (")
                .append(mockData.getId()).append(",'")
                .append(mockData.getFirstName()).append("','")
                .append(mockData.getLastName().replace("'","")).append("','")
                .append(mockData.getEmail()).append("','")
                .append(mockData.getGender()).append("','")
                .append(mockData.getIpAddress()).append("')")
        ;
        return sb.toString();
    }


}
