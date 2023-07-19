package fa.training.service.exporter;

import fa.training.dto.MovieDTO;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class MovieExcelExporter {
    private XSSFWorkbook workbook;
    private XSSFSheet sheet;
    private List<MovieDTO> listMovie;
    public MovieExcelExporter(List<MovieDTO> listMovie) {
        this.listMovie = listMovie;
        workbook = new XSSFWorkbook();
    }


    private void writeHeaderLine() {
        sheet = workbook.createSheet("Users");

        Row row = sheet.createRow(0);

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(16);
        style.setFont(font);

        createCell(row, 0, "name", style);
        createCell(row, 1, "description", style);
        createCell(row, 2, "lengthMinute", style);
        createCell(row, 3, "rating", style);
        createCell(row, 4, "categoryDTO", style);

    }

    private void createCell(Row row, int columnCount, Object value, CellStyle style) {
        sheet.autoSizeColumn(columnCount);
        Cell cell = row.createCell(columnCount);
        if (value instanceof Integer) {
            cell.setCellValue((Integer) value);
        } else if (value instanceof Boolean) {
            cell.setCellValue((Boolean) value);
        }else {
            cell.setCellValue((String) value);
        }
        cell.setCellStyle(style);
    }

    private void writeDataLines() {
        int rowCount = 1;

        CellStyle style = workbook.createCellStyle();
        XSSFFont font = workbook.createFont();
        font.setFontHeight(14);
        style.setFont(font);

        for (MovieDTO movieDTO : listMovie) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;

            createCell(row, columnCount++, movieDTO.getName(), style);
            createCell(row, columnCount++, movieDTO.getDescription(), style);
            createCell(row, columnCount++, movieDTO.getLengthMinute(), style);
            createCell(row, columnCount++, String.valueOf(movieDTO.getRating()), style);
            createCell(row, columnCount++, movieDTO.getCategoryDTO(), style);

        }
    }

    public void export(HttpServletResponse response) throws IOException {
        writeHeaderLine();
        writeDataLines();

        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();

        outputStream.close();

    }


}
