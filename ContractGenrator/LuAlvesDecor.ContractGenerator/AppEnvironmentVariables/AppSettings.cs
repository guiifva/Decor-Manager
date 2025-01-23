using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace ContractGenrator;
public class AppSettings
{
    public ContractSettings ContractSettings { get; set; } = new();
    public CompanyInfo CompanyInfo { get; set; } = new();
    public PaymentInfo PaymentInfo { get; set; } = new();
}
