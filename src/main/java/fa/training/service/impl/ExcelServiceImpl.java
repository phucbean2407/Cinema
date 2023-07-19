package fa.training.service.impl;

import fa.training.dto.MovieDTO;
import fa.training.dto.ResourceDTO;
import fa.training.service.ExcelService;
import fa.training.service.MovieService;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class ExcelServiceImpl implements ExcelService {
    private final MovieService movieService;

    public ExcelServiceImpl(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public ResourceDTO exportMovies() {
        List<MovieDTO> movieList= movieService.findAllFMovies();
        Resource resource= prepareExcel(movieList);
        return ResourceDTO.builder().resource(resource).
                mediaType(MediaType.parseMediaType
                        ("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet")).build();
    }
    private Resource prepareExcel(List<MovieDTO> movieList){
        Workbook workbook=new XSSFWorkbook();
        Sheet sheet=workbook.createSheet("USERS");
        prepareHeaders(workbook,sheet,"name", "description", "lengthMinute", "rating", "categoryDTO");
        populateUserData(workbook,sheet,movieList);

        try(ByteArrayOutputStream byteArrayOutputStream
                    =new ByteArrayOutputStream()){

            workbook.write(byteArrayOutputStream);
            return new
                    ByteArrayResource
                    (byteArrayOutputStream.toByteArray());
        }catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException
                    ("Error while generating excel.");
        }
    }

    private void prepareHeaders(Workbook workbook,
                                Sheet sheet, String... headers) {

        Row headerRow=sheet.createRow(0);
        Font font=workbook.createFont();
        font.setBold(true);
        font.setFontName("Arial");
        CellStyle cellStyle=workbook.createCellStyle();
        cellStyle.setFont(font);
        int columnNo=0;
        for(String header:headers){
            Cell headerCell=headerRow.createCell(columnNo++);
            headerCell.setCellValue(header);
            headerCell.setCellStyle(cellStyle);
        }

    }
    
    private void populateCell(Sheet sheet,Row row,int columnNo,String value,CellStyle cellStyle){
        Cell cell=row.createCell(columnNo);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(value);
        sheet.autoSizeColumn(columnNo);
    }

    private void populateUserData(Workbook workbook, Sheet sheet,
             List<MovieDTO> movieList) {

        int rowNo=1;
        Font font=workbook.createFont();
        font.setFontName("Arial");

        CellStyle cellStyle=workbook.createCellStyle();
        cellStyle.setFont(font);

        for(MovieDTO movieDTO:movieList){
            int columnNo=0;
            Row row=sheet.createRow(rowNo);
            populateCell(sheet,row,columnNo++, movieDTO.getName(), cellStyle);
            populateCell(sheet,row,columnNo++, movieDTO.getDescription(),cellStyle);
            populateCell(sheet,row,columnNo++, String.valueOf(movieDTO.getLengthMinute()),cellStyle);
            populateCell(sheet,row,columnNo++, String.valueOf(movieDTO.getRating()),cellStyle);
            populateCell(sheet,row,columnNo++, movieDTO.getCategoryDTO(), cellStyle);

            rowNo++;
        }
    }


}
