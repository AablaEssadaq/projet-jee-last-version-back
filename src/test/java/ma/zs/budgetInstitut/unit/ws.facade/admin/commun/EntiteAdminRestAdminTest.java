package ma.zs.budgetInstitut.unit.ws.facade.admin.commun;

import ma.zs.budgetInstitut.bean.core.commun.EntiteAdmin;
import ma.zs.budgetInstitut.service.impl.admin.commun.EntiteAdminAdminServiceImpl;
import ma.zs.budgetInstitut.ws.facade.admin.commun.EntiteAdminRestAdmin;
import ma.zs.budgetInstitut.ws.converter.commun.EntiteAdminConverter;
import ma.zs.budgetInstitut.ws.dto.commun.EntiteAdminDto;
import org.aspectj.lang.annotation.Before;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EntiteAdminRestAdminTest {

    private MockMvc mockMvc;

    @Mock
    private EntiteAdminAdminServiceImpl service;
    @Mock
    private EntiteAdminConverter converter;

    @InjectMocks
    private EntiteAdminRestAdmin controller;

    @Before("")
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }


    @Test
    public void itShouldFindAllEntiteAdminTest() throws Exception {
        // Mock the service to return an empty list
        when(service.findAll()).thenReturn(Collections.emptyList());
        when(converter.toDto(Collections.emptyList())).thenReturn(Collections.emptyList());

        // Call the controller method
        ResponseEntity<List<EntiteAdminDto>> result = controller.findAll();

        // Verify the result
        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());

        // Response body should be empty list
        List<EntiteAdminDto> responseBody = result.getBody();
        assertNotNull(responseBody);
        assertEquals(0, responseBody.size());
    }

    @Test
    public void itShouldSaveEntiteAdminTest() throws Exception {
        // Mock data
        EntiteAdminDto requestDto = new EntiteAdminDto();
        EntiteAdmin entity = new EntiteAdmin();
        EntiteAdmin saved = new EntiteAdmin();
        EntiteAdminDto savedDto = new EntiteAdminDto();

        // Mock the converter to return the entiteAdmin object when converting from DTO
        when(converter.toItem(requestDto)).thenReturn(entity);

        // Mock the service to return the saved client
        when(service.create(entity)).thenReturn(saved);

        // Mock the converter to return the saved entiteAdmin DTO
        when(converter.toDto(saved)).thenReturn(savedDto);

        // Call the controller method
        ResponseEntity<EntiteAdminDto> result = controller.save(requestDto);

        // Verify the result
        assertEquals(HttpStatus.CREATED, result.getStatusCode());

        // Verify the response body
        EntiteAdminDto responseBody = result.getBody();
        assertNotNull(responseBody);

        // Add assertions to compare the response body with the saved entiteAdmin DTO
        assertEquals(savedDto.getCode(), responseBody.getCode());
        assertEquals(savedDto.getLibelle(), responseBody.getLibelle());
    }

}
