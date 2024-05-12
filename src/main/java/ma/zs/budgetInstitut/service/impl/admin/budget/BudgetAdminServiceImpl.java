package ma.zs.budgetInstitut.service.impl.admin.budget;


import ma.zs.budgetInstitut.bean.core.budget.Budget;
import ma.zs.budgetInstitut.dao.criteria.core.budget.BudgetCriteria;
import ma.zs.budgetInstitut.dao.facade.core.budget.BudgetDao;
import ma.zs.budgetInstitut.dao.specification.core.budget.BudgetSpecification;
import ma.zs.budgetInstitut.service.facade.admin.budget.BudgetAdminService;
import ma.zs.budgetInstitut.zynerator.service.AbstractServiceImpl;
import ma.zs.budgetInstitut.zynerator.util.ListUtil;
import org.springframework.stereotype.Service;
import java.util.List;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;





import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ma.zs.budgetInstitut.service.facade.admin.budget.BudgetEntiteAdminAdminService ;
import ma.zs.budgetInstitut.bean.core.budget.BudgetEntiteAdmin ;

import java.util.List;
@Service
public class BudgetAdminServiceImpl extends AbstractServiceImpl<Budget, BudgetCriteria, BudgetDao> implements BudgetAdminService {


    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class, readOnly = false)
    public Budget create(Budget t) {
        Budget saved= super.create(t);
        if (saved != null && t.getBudgetEntiteAdmins() != null) {
                t.getBudgetEntiteAdmins().forEach(element-> {
                element.setBudget(saved);
                budgetEntiteAdminService.create(element);
            });
        }
        return saved;

    }

    public Budget findWithAssociatedLists(Long id){
        Budget result = dao.findById(id).orElse(null);
        if(result!=null && result.getId() != null) {
            result.setBudgetEntiteAdmins(budgetEntiteAdminService.findByBudgetId(id));
        }
        return result;
    }
    @Transactional
    public void deleteAssociatedLists(Long id) {
        budgetEntiteAdminService.deleteByBudgetId(id);
    }


    public void updateWithAssociatedLists(Budget budget){
    if(budget !=null && budget.getId() != null){
            List<List<BudgetEntiteAdmin>> resultBudgetEntiteAdmins= budgetEntiteAdminService.getToBeSavedAndToBeDeleted(budgetEntiteAdminService.findByBudgetId(budget.getId()),budget.getBudgetEntiteAdmins());
            budgetEntiteAdminService.delete(resultBudgetEntiteAdmins.get(1));
            ListUtil.emptyIfNull(resultBudgetEntiteAdmins.get(0)).forEach(e -> e.setBudget(budget));
            budgetEntiteAdminService.update(resultBudgetEntiteAdmins.get(0),true);
        }
    }
     
    public BigDecimal calculateRemainingAmount(Long budgetId) {
        Budget budget = dao.findById(budgetId).orElse(null);
        if (budget == null) {
            return null;
        }
        
        BigDecimal budgetAmount = new BigDecimal(budget.getMontant().toString());
        
      
        BigDecimal totalAllocatedAmount = budget.getBudgetEntiteAdmins().stream()
                .map(BudgetEntiteAdmin::getMontantEntite)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    
        BigDecimal remainingAmount = budgetAmount.subtract(totalAllocatedAmount);
    
        return remainingAmount.setScale(2, RoundingMode.HALF_UP);
    }
 












    public void configure() {
        super.configure(Budget.class, BudgetSpecification.class);
    }


    @Autowired
    private BudgetEntiteAdminAdminService budgetEntiteAdminService ;

    public BudgetAdminServiceImpl(BudgetDao dao) {
        super(dao);
    }



    

}
