package ma.zs.budgetInstitut.service.facade.admin.budget;

import java.math.BigDecimal;
import java.util.List;
import ma.zs.budgetInstitut.bean.core.budget.BudgetEntiteAdmin;
import ma.zs.budgetInstitut.dao.criteria.core.budget.BudgetEntiteAdminCriteria;
import ma.zs.budgetInstitut.zynerator.service.IService;



public interface BudgetEntiteAdminAdminService extends  IService<BudgetEntiteAdmin,BudgetEntiteAdminCriteria>  {

    List<BudgetEntiteAdmin> findByEntiteAdminId(Long id);
    int deleteByEntiteAdminId(Long id);
    long countByEntiteAdminId(Long id);
    List<BudgetEntiteAdmin> findByBudgetId(Long id);
    int deleteByBudgetId(Long id);
    long countByBudgetId(Long id);

      



}
