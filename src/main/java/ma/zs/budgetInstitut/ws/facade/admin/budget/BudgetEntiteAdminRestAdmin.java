package  ma.zs.budgetInstitut.ws.facade.admin.budget;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import ma.zs.budgetInstitut.bean.core.budget.BudgetEntiteAdmin;
import ma.zs.budgetInstitut.dao.criteria.core.budget.BudgetEntiteAdminCriteria;
import ma.zs.budgetInstitut.service.facade.admin.budget.BudgetEntiteAdminAdminService;
import ma.zs.budgetInstitut.service.impl.admin.budget.BudgetAdminServiceImpl;
import ma.zs.budgetInstitut.ws.converter.budget.BudgetEntiteAdminConverter;
import ma.zs.budgetInstitut.ws.dto.budget.BudgetEntiteAdminDto;
import ma.zs.budgetInstitut.zynerator.controller.AbstractController;
import ma.zs.budgetInstitut.zynerator.dto.AuditEntityDto;
import ma.zs.budgetInstitut.zynerator.util.PaginatedList;


import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import ma.zs.budgetInstitut.zynerator.process.Result;


import org.springframework.web.multipart.MultipartFile;
import ma.zs.budgetInstitut.zynerator.dto.FileTempDto;

@RestController
@RequestMapping("/api/admin/budgetEntiteAdmin/")
public class BudgetEntiteAdminRestAdmin  extends AbstractController<BudgetEntiteAdmin, BudgetEntiteAdminDto, BudgetEntiteAdminCriteria, BudgetEntiteAdminAdminService, BudgetEntiteAdminConverter> {

   

    @Operation(summary = "upload one budgetEntiteAdmin")
    @RequestMapping(value = "upload", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<FileTempDto> uploadFileAndGetChecksum(@RequestBody MultipartFile file) throws Exception {
        return super.uploadFileAndGetChecksum(file);
    }
    @Operation(summary = "upload multiple budgetEntiteAdmins")
    @RequestMapping(value = "upload-multiple", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<List<FileTempDto>> uploadMultipleFileAndGetChecksum(@RequestBody MultipartFile[] files) throws Exception {
        return super.uploadMultipleFileAndGetChecksum(files);
    }

    @Operation(summary = "Finds a list of all budgetEntiteAdmins")
    @GetMapping("")
    public ResponseEntity<List<BudgetEntiteAdminDto>> findAll() throws Exception {
        return super.findAll();
    }



    @Operation(summary = "Saves the specified  budgetEntiteAdmin")
    @PostMapping("")
    public ResponseEntity<BudgetEntiteAdminDto> save(@RequestBody BudgetEntiteAdminDto dto) throws Exception {
        return super.save(dto);
    }

    @Operation(summary = "Updates the specified  budgetEntiteAdmin")
    @PutMapping("")
    public ResponseEntity<BudgetEntiteAdminDto> update(@RequestBody BudgetEntiteAdminDto dto) throws Exception {
        return super.update(dto);
    }

    @Operation(summary = "Delete list of budgetEntiteAdmin")
    @PostMapping("multiple")
    public ResponseEntity<List<BudgetEntiteAdminDto>> delete(@RequestBody List<BudgetEntiteAdminDto> listToDelete) throws Exception {
        return super.delete(listToDelete);
    }
    @Operation(summary = "Delete the specified budgetEntiteAdmin")
    @DeleteMapping("")
    public ResponseEntity<BudgetEntiteAdminDto> delete(@RequestBody BudgetEntiteAdminDto dto) throws Exception {
            return super.delete(dto);
    }

    @Operation(summary = "Delete the specified budgetEntiteAdmin")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws Exception {
        return super.deleteById(id);
    }
    @Operation(summary = "Delete multiple budgetEntiteAdmins by ids")
    @DeleteMapping("multiple/id")
    public ResponseEntity<List<Long>> deleteByIdIn(@RequestBody List<Long> ids) throws Exception {
            return super.deleteByIdIn(ids);
     }


    @Operation(summary = "find by entiteAdmin id")
    @GetMapping("entiteAdmin/id/{id}")
    public List<BudgetEntiteAdminDto> findByEntiteAdminId(@PathVariable Long id){
        return findDtos(service.findByEntiteAdminId(id));
    }
    @Operation(summary = "delete by entiteAdmin id")
    @DeleteMapping("entiteAdmin/id/{id}")
    public int deleteByEntiteAdminId(@PathVariable Long id){
        return service.deleteByEntiteAdminId(id);
    }
    @Operation(summary = "find by budget id")
    @GetMapping("budget/id/{id}")
    public List<BudgetEntiteAdminDto> findByBudgetId(@PathVariable Long id){
        return findDtos(service.findByBudgetId(id));
    }
    @Operation(summary = "delete by budget id")
    @DeleteMapping("budget/id/{id}")
    public int deleteByBudgetId(@PathVariable Long id){
        return service.deleteByBudgetId(id);
    }

    @Operation(summary = "Finds a budgetEntiteAdmin and associated list by id")
    @GetMapping("id/{id}")
    public ResponseEntity<BudgetEntiteAdminDto> findById(@PathVariable Long id) {
        return super.findById(id);
    }

    @Operation(summary = "Finds budgetEntiteAdmins by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<BudgetEntiteAdminDto>> findByCriteria(@RequestBody BudgetEntiteAdminCriteria criteria) throws Exception {
        return super.findByCriteria(criteria);
    }

    @Operation(summary = "Finds paginated budgetEntiteAdmins by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody BudgetEntiteAdminCriteria criteria) throws Exception {
        return super.findPaginatedByCriteria(criteria);
    }

    @Operation(summary = "Exports budgetEntiteAdmins by criteria")
    @PostMapping("export")
    public ResponseEntity<InputStreamResource> export(@RequestBody BudgetEntiteAdminCriteria criteria) throws Exception {
        return super.export(criteria);
    }

    @Operation(summary = "Gets budgetEntiteAdmin data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody BudgetEntiteAdminCriteria criteria) throws Exception {
        return super.getDataSize(criteria);
    }



    public BudgetEntiteAdminRestAdmin (BudgetEntiteAdminAdminService service, BudgetEntiteAdminConverter converter) {
        super(service, converter);
    }




}
