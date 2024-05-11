package ma.zs.budgetInstitut.service.impl.admin.expressionbesoin;


import ma.zs.budgetInstitut.bean.core.expressionbesoin.ExpressionBesoin;
import ma.zs.budgetInstitut.dao.criteria.core.expressionbesoin.ExpressionBesoinCriteria;
import ma.zs.budgetInstitut.dao.facade.core.expressionbesoin.ExpressionBesoinDao;
import ma.zs.budgetInstitut.dao.specification.core.expressionbesoin.ExpressionBesoinSpecification;
import ma.zs.budgetInstitut.service.facade.admin.expressionbesoin.ExpressionBesoinAdminService;
import ma.zs.budgetInstitut.zynerator.service.AbstractServiceImpl;
import ma.zs.budgetInstitut.zynerator.util.ListUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ma.zs.budgetInstitut.service.facade.admin.commun.EntiteAdminAdminService;
import ma.zs.budgetInstitut.bean.core.commun.EntiteAdmin;
import ma.zs.budgetInstitut.service.facade.admin.expressionbesoin.ExpressionBesoinDetailAdminService;
import ma.zs.budgetInstitut.bean.core.expressionbesoin.ExpressionBesoinDetail;
import ma.zs.budgetInstitut.service.facade.admin.commun.EmployeAdminService;
import ma.zs.budgetInstitut.bean.core.commun.Employe;

import java.util.List;

@Service
public class ExpressionBesoinAdminServiceImpl extends AbstractServiceImpl<ExpressionBesoin, ExpressionBesoinCriteria, ExpressionBesoinDao> implements ExpressionBesoinAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public ExpressionBesoin create(ExpressionBesoin t) {
        ExpressionBesoin saved = super.create(t);
        if (saved != null && t.getExpressionBesoinDetails() != null) {
            t.getExpressionBesoinDetails().forEach(element -> {
                element.setExpressionBesoin(saved);
                expressionBesoinDetailService.create(element);
            });
        }
        return saved;

    }

    public ExpressionBesoin findWithAssociatedLists(Long id) {
        ExpressionBesoin result = dao.findById(id).orElse(null);
        if (result != null && result.getId() != null) {
            result.setExpressionBesoinDetails(expressionBesoinDetailService.findByExpressionBesoinId(id));
        }
        return result;
    }

    @Transactional
    public void deleteAssociatedLists(Long id) {
        expressionBesoinDetailService.deleteByExpressionBesoinId(id);
    }


    public void updateWithAssociatedLists(ExpressionBesoin expressionBesoin) {
        if (expressionBesoin != null && expressionBesoin.getId() != null) {
            List<List<ExpressionBesoinDetail>> resultExpressionBesoinDetails = expressionBesoinDetailService.getToBeSavedAndToBeDeleted(expressionBesoinDetailService.findByExpressionBesoinId(expressionBesoin.getId()), expressionBesoin.getExpressionBesoinDetails());
            expressionBesoinDetailService.delete(resultExpressionBesoinDetails.get(1));
            ListUtil.emptyIfNull(resultExpressionBesoinDetails.get(0)).forEach(e -> e.setExpressionBesoin(expressionBesoin));
            expressionBesoinDetailService.update(resultExpressionBesoinDetails.get(0), true);
        }
    }


    public void findOrSaveAssociatedObject(ExpressionBesoin t) {
        if (t != null) {
            t.setEmploye(employeService.findOrSave(t.getEmploye()));
            t.setEntiteAdmin(entiteAdminService.findOrSave(t.getEntiteAdmin()));
        }
    }

    public List<ExpressionBesoin> findByEmployeId(Long id) {
        return dao.findByEmployeId(id);
    }

    public int deleteByEmployeId(Long id) {
        return dao.deleteByEmployeId(id);
    }

    public long countByEmployeId(Long id) {
        return dao.countByEmployeId(id);
    }

    public List<ExpressionBesoin> findByEntiteAdminId(Long id) {
        return dao.findByEntiteAdminId(id);
    }

    public int deleteByEntiteAdminId(Long id) {
        return dao.deleteByEntiteAdminId(id);
    }

    public long countByEntiteAdminId(Long id) {
        return dao.countByEntiteAdminId(id);
    }

    public void accorderChef(Long idExpressionBesoin, BigDecimal qteDemandee, BigDecimal qteAccordee, BigDecimal qteLivre) {
        // Récupérer l'expression de besoin correspondant à l'ID
        ExpressionBesoin expressionBesoin = dao.findById(idExpressionBesoin).orElse(null);
        if (expressionBesoin != null) {
            // Parcourir tous les détails de l'expression de besoin et accorder les quantités pour chaque détail
            for (ExpressionBesoinDetail detail : expressionBesoin.getExpressionBesoinDetails()) {
                expressionBesoinDetailAdminService.accorderQuantitesChef(detail.getId(), qteDemandee, qteAccordee, qteLivre);
            }
        }
    }


    public void configure() {
        super.configure(ExpressionBesoin.class, ExpressionBesoinSpecification.class);
    }

    @Autowired
    private ExpressionBesoinDetailAdminServiceImpl expressionBesoinDetailAdminService;
    @Autowired
    private EntiteAdminAdminService entiteAdminService;
    @Autowired
    private ExpressionBesoinDetailAdminService expressionBesoinDetailService;
    @Autowired
    private EmployeAdminService employeService;

    public ExpressionBesoinAdminServiceImpl(ExpressionBesoinDao dao) {
        super(dao);
    }

}
