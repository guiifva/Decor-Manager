namespace ContractGenrator;

public class ContractSettings
{
    public decimal DownPaymentPercentage { get; set; } = 0.3m;
    public int? LateReturnPenaltyPercentage { get; set; } = 10;
    public ContractTerms ContractTerms { get; set; } = new();
}

public class ContractTerms
{
    public string? BetweenParties { get; set; }
    public string? Lessor { get; set; }
    public string? Lessee { get; set; }
}