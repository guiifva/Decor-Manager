using Microsoft.Extensions.Hosting;
using QuestPDF.Infrastructure;

var host = new HostBuilder()
    .ConfigureServices(services => {
    })
    .Build();

QuestPDF.Settings.License = LicenseType.Community;

host.Run();
