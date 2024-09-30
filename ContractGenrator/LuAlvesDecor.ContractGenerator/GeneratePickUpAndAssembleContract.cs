using System.Collections.Generic;
using System.Net;
using System.Text.Json;
using System.Text.Json.Serialization;
using Microsoft.Azure.Functions.Worker;
using Microsoft.Azure.Functions.Worker.Http;
using Microsoft.Extensions.Logging;

namespace ContractGenrator;

public class GeneratePickUpAndAssembleContract
{
    private readonly ILogger _logger;
    private readonly AssembleDecorationContractPdfGenerator _generator;

    public GeneratePickUpAndAssembleContract(ILoggerFactory loggerFactory)
    {
        _logger = loggerFactory.CreateLogger<GeneratePickUpAndAssembleContract>();
        _generator = new AssembleDecorationContractPdfGenerator();
    }

    [Function("GeneratePickUpAndAssembleContract")]
    public HttpResponseData Run([HttpTrigger(AuthorizationLevel.Function, "post")] HttpRequestData req, FunctionContext executionContext)
    {
        _logger.LogInformation("C# HTTP trigger function processed a request.");

        var request = JsonSerializer.Deserialize<GenerateAssembleDecorationContractRequestDto>(req.Body);
        if (request == null)
        {
            return req.CreateResponse(HttpStatusCode.BadRequest);
        }
        
        var pdfFile = _generator.GetPdfFile(request);
        
        var response = req.CreateResponse(HttpStatusCode.OK);
        response.Headers.Add("Access-Control-Expose-Headers", "Content-Disposition");
        response.Headers.Add("Content-Type", "application/pdf");
        response.Headers.Add("Content-Disposition", $"attachment;filename=\"{pdfFile.GetFileName()}\"");
        
        response.Body = new MemoryStream(pdfFile.Bytes);

        return response;
    }
}