namespace ContractGenrator;

public record GenerateAssembleDecorationContractRequestDto(
    string CustomerName,
    string CustomerDocument,
    string CustomerPhone,
    string CustomerAddress,
    string DecorationTheme,
    string DecorationSetupAddress,
    DateTime DecorationScheduleDateAndTime,
    string DecorationDevolutionDate,
    int DecorationValue,
    List<GenerateAssembleDecorationContractItemRequestDto> Items
);