package ma.zs.budgetInstitut.service.facade.admin.budget;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import ma.zs.budgetInstitut.bean.core.budget.Budget;
import ma.zs.budgetInstitut.dao.criteria.core.budget.BudgetCriteria;
import ma.zs.budgetInstitut.zynerator.service.IService;


@Service
public interface BudgetAdminService extends  IService<Budget,BudgetCriteria>  {

    public BigDecimal calculateRemainingAmount(Long budgetId);


}
