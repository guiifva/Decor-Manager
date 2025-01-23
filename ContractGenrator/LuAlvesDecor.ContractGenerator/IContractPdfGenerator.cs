namespace ContractGenrator;

public interface IContractPdfGenerator
{
    PdfFile GetPdfFile(GenerateAssembleDecorationContractRequestDto assembleDecarationContractRequestDto);
}