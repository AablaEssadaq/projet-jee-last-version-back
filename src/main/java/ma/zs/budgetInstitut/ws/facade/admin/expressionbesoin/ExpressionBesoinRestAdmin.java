package  ma.zs.budgetInstitut.ws.facade.admin.expressionbesoin;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import ma.zs.budgetInstitut.bean.core.expressionbesoin.ExpressionBesoin;
import ma.zs.budgetInstitut.dao.criteria.core.expressionbesoin.ExpressionBesoinCriteria;
import ma.zs.budgetInstitut.service.facade.admin.expressionbesoin.ExpressionBesoinAdminService;
import ma.zs.budgetInstitut.service.impl.admin.expressionbesoin.ExpressionBesoinAdminServiceImpl;
import ma.zs.budgetInstitut.ws.converter.expressionbesoin.ExpressionBesoinConverter;
import ma.zs.budgetInstitut.ws.dto.expressionbesoin.ExpressionBesoinDto;
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
@RequestMapping("/api/admin/expressionBesoin/")
public class ExpressionBesoinRestAdmin  extends AbstractController<ExpressionBesoin, ExpressionBesoinDto, ExpressionBesoinCriteria, ExpressionBesoinAdminService, ExpressionBesoinConverter> {

@Autowired
private ExpressionBesoinAdminServiceImpl expressionBesoinAdminService;
    @PostMapping("/accorder-chef")
    public ResponseEntity<?> accorderChef(@RequestParam Long idExpressionBesoin,
                                          @RequestParam BigDecimal qteDemandee,
                                          @RequestParam BigDecimal qteAccordee,
                                          @RequestParam BigDecimal qteLivre) {
        expressionBesoinAdminService.accorderChef(idExpressionBesoin, qteDemandee, qteAccordee, qteLivre);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "upload one expressionBesoin")
    @RequestMapping(value = "upload", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<FileTempDto> uploadFileAndGetChecksum(@RequestBody MultipartFile file) throws Exception {
        return super.uploadFileAndGetChecksum(file);
    }
    @Operation(summary = "upload multiple expressionBesoins")
    @RequestMapping(value = "upload-multiple", method = RequestMethod.POST, consumes = "multipart/form-data")
    public ResponseEntity<List<FileTempDto>> uploadMultipleFileAndGetChecksum(@RequestBody MultipartFile[] files) throws Exception {
        return super.uploadMultipleFileAndGetChecksum(files);
    }

    @Operation(summary = "Finds a list of all expressionBesoins")
    @GetMapping("")
    public ResponseEntity<List<ExpressionBesoinDto>> findAll() throws Exception {
        return super.findAll();
    }



    @Operation(summary = "Saves the specified  expressionBesoin")
    @PostMapping("")
    public ResponseEntity<ExpressionBesoinDto> save(@RequestBody ExpressionBesoinDto dto) throws Exception {
        return super.save(dto);
    }

    @Operation(summary = "Updates the specified  expressionBesoin")
    @PutMapping("")
    public ResponseEntity<ExpressionBesoinDto> update(@RequestBody ExpressionBesoinDto dto) throws Exception {
        return super.update(dto);
    }

    @Operation(summary = "Delete list of expressionBesoin")
    @PostMapping("multiple")
    public ResponseEntity<List<ExpressionBesoinDto>> delete(@RequestBody List<ExpressionBesoinDto> listToDelete) throws Exception {
        return super.delete(listToDelete);
    }
    @Operation(summary = "Delete the specified expressionBesoin")
    @DeleteMapping("")
    public ResponseEntity<ExpressionBesoinDto> delete(@RequestBody ExpressionBesoinDto dto) throws Exception {
            return super.delete(dto);
    }

    @Operation(summary = "Delete the specified expressionBesoin")
    @DeleteMapping("id/{id}")
    public ResponseEntity<Long> deleteById(@PathVariable Long id) throws Exception {
        return super.deleteById(id);
    }
    @Operation(summary = "Delete multiple expressionBesoins by ids")
    @DeleteMapping("multiple/id")
    public ResponseEntity<List<Long>> deleteByIdIn(@RequestBody List<Long> ids) throws Exception {
            return super.deleteByIdIn(ids);
     }


    @Operation(summary = "find by employe id")
    @GetMapping("employe/id/{id}")
    public List<ExpressionBesoinDto> findByEmployeId(@PathVariable Long id){
        return findDtos(service.findByEmployeId(id));
    }
    @Operation(summary = "delete by employe id")
    @DeleteMapping("employe/id/{id}")
    public int deleteByEmployeId(@PathVariable Long id){
        return service.deleteByEmployeId(id);
    }
    @Operation(summary = "find by entiteAdmin id")
    @GetMapping("entiteAdmin/id/{id}")
    public List<ExpressionBesoinDto> findByEntiteAdminId(@PathVariable Long id){
        return findDtos(service.findByEntiteAdminId(id));
    }
    @Operation(summary = "delete by entiteAdmin id")
    @DeleteMapping("entiteAdmin/id/{id}")
    public int deleteByEntiteAdminId(@PathVariable Long id){
        return service.deleteByEntiteAdminId(id);
    }

    @Operation(summary = "Finds a expressionBesoin and associated list by id")
    @GetMapping("id/{id}")
    public ResponseEntity<ExpressionBesoinDto> findById(@PathVariable Long id) {
        return super.findWithAssociatedLists(id);
    }

    @Operation(summary = "Finds expressionBesoins by criteria")
    @PostMapping("find-by-criteria")
    public ResponseEntity<List<ExpressionBesoinDto>> findByCriteria(@RequestBody ExpressionBesoinCriteria criteria) throws Exception {
        return super.findByCriteria(criteria);
    }

    @Operation(summary = "Finds paginated expressionBesoins by criteria")
    @PostMapping("find-paginated-by-criteria")
    public ResponseEntity<PaginatedList> findPaginatedByCriteria(@RequestBody ExpressionBesoinCriteria criteria) throws Exception {
        return super.findPaginatedByCriteria(criteria);
    }

    @Operation(summary = "Exports expressionBesoins by criteria")
    @PostMapping("export")
    public ResponseEntity<InputStreamResource> export(@RequestBody ExpressionBesoinCriteria criteria) throws Exception {
        return super.export(criteria);
    }

    @Operation(summary = "Gets expressionBesoin data size by criteria")
    @PostMapping("data-size-by-criteria")
    public ResponseEntity<Integer> getDataSize(@RequestBody ExpressionBesoinCriteria criteria) throws Exception {
        return super.getDataSize(criteria);
    }



    public ExpressionBesoinRestAdmin (ExpressionBesoinAdminService service, ExpressionBesoinConverter converter) {
        super(service, converter);
    }




}
