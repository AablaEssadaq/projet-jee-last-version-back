package  ma.zs.budgetInstitut.ws.facade.admin.budget;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import ma.zs.budgetInstitut.bean.core.budget.Budget;
import ma.zs.budgetInstitut.dao.criteria.core.budget.BudgetCriteria;
import ma.zs.budgetInstitut.service.facade.admin.budget.BudgetAdminService;
import ma.zs.budgetInstitut.ws.converter.budget.BudgetConverter;
import ma.zs.budgetInstitut.ws.dto.budget.BudgetDto;
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
@RequestMapping("/api/admin/budget/")
public class BudgetRestAdmin  extends AbstractController<Budget, BudgetDto, BudgetCriteria, BudgetAdminService, BudgetConverter> {


     @GetMapping("/{budgetId}/remaining-amount")
     public ResponseEntity<Long> calculateRemainingAmount(@PathVariable Long budgetId) throws Exception {
        return super.calculateRemainingAmount(budgetId);
    }


    @Operation(summary = "upload one budget")
    @RequestMapping(value = "upload", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<FileTempDto> uploadFileAndGetChecksum(@RequestBody MultipartFile file) throws Exception {
        return super.uploadFileAndGetChecksum(file);
    }
    @Operation(summary = "upload multiple budgets")
    @RequestMapping(value = "upload-multiple", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<List<FileTempDto>> uploadMultipleFileAndGetChecksum(@RequestBody MultipartFile[] files) throws Exception {
        return super.uploadMultipleFileAndGetChecksum(files);
    }

    @Operation(summary = "Finds a list of all budgets")
    @GetMapping("")
    public ResponseEntity<List<BudgetDto>> findAll() throws Exception {
        return super.findAll();
    }



    @Operation(summary = "Saves the specified  budget")
    @PostMapping("")
    public ResponseEntity<BudgetDto> save(@RequestBody BudgetDto dto) throws Exception {
        return super.save(dto);
    }

    @Operation(summary = "Updates the specified  budget")
    @PutMapping("")
    public ResponseEntity<BudgetDto> update(@RequestBody BudgetDto dto) throws Exception {
        return super.update(dto);
    }

    @Operation(summary = "Delete list of budget")
    @PostMapping("multiple")
    public ResponseEntity<List<BudgetDto>> delete(@RequestBody List<BudgetDto> listToDelete) throws Exception {
        return super.delete(listToDelete);
    }
    @Operation(summary = "Delete the specified budget")
    @DeleteMapping("")
    public ResponseEntity<BudgetDto> delete(@RequestBody BudgetDto dto) throws Exception {
            return super.delete(dto);
    }

    @Operation(summary = "Delete the specified budget")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws Exception {
        return super.deleteById(id);
    }
    @Operation(summary = "Delete multiple budgets by ids")
    @DeleteMapping("multiple/id")
    public ResponseEntity<List<Long>> deleteByIdIn(@RequestBody List<Long> ids) throws Exception {
            return super.deleteByIdIn(ids);
     }



    @Operation(summary = "Finds a budget and associated list by id")
    @GetMapping("id/{id}")
    public ResponseEntity<BudgetDto> findById(@PathVariable Long id) {
        return super.findWithAssociatedLists(id);
    }

    @Operation(summary = "Finds budgets by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<BudgetDto>> findByCriteria(@RequestBody BudgetCriteria criteria) throws Exception {
        return super.findByCriteria(criteria);
    }

    @Operation(summary = "Finds paginated budgets by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody BudgetCriteria criteria) throws Exception {
        return super.findPaginatedByCriteria(criteria);
    }

    @Operation(summary = "Exports budgets by criteria")
    @PostMapping("export")
    public ResponseEntity<InputStreamResource> export(@RequestBody BudgetCriteria criteria) throws Exception {
        return super.export(criteria);
    }

    @Operation(summary = "Gets budget data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody BudgetCriteria criteria) throws Exception {
        return super.getDataSize(criteria);
    }



    public BudgetRestAdmin (BudgetAdminService service, BudgetConverter converter) {
        super(service, converter);
    }




}
