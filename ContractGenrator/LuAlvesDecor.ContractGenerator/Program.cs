using Microsoft.Extensions.Hosting;
using QuestPDF.Infrastructure;

var host = new HostBuilder()
    .ConfigureFunctionsWorkerDefaults()
    .ConfigureServices(services => { })
    .Build();

QuestPDF.Settings.License = LicenseType.Community;

host.Run();
