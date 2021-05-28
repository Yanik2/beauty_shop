package com.example.beauty_shop.controller.command;

import com.example.beauty_shop.controller.validator.DateValidator;
import com.example.beauty_shop.entity.AdminTableItem;
import com.example.beauty_shop.service.defaultimpl.HomepageServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.beauty_shop.constants.Constants.*;

public class FilterByDateCommand implements Command {
    private final HomepageServiceImpl homepageServiceImpl = new HomepageServiceImpl();

    @Override
    public Map<String, Object> execute(HttpServletRequest request, HttpServletResponse response) {
        String date = request.getParameter(DATE);
        Map<String, Object> map = new HashMap<>();
        map.put(PAGE, HOMEPAGE + ADMIN_HOME);
        if(!DateValidator.validate(date)) {
            map.put(SHOWLINK, true);
            map.put(MESSAGE, CORRECT_DATE);
            return map;
        }
        List<AdminTableItem> adminTable = homepageServiceImpl.filterByDate(date);

        if(adminTable.size() != 0) {
            map.put(CATALOG, adminTable);
        } else {
            map.put(SHOWLINK, true);
            map.put(MESSAGE, NO_APPOINTMENTS);
        }
        return map;
    }
}
