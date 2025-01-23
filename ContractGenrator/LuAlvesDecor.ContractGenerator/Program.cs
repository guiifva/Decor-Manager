using ContractGenrator;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;
using Microsoft.Extensions.Hosting;
using QuestPDF.Infrastructure;

var host = new HostBuilder()
    .ConfigureFunctionsWorkerDefaults()
    .ConfigureAppConfiguration(configureDelegate =>
    {
        configureDelegate.SetBasePath(Directory.GetCurrentDirectory())
            .AddJsonFile("local.settings.json", optional: true, reloadOnChange: false)
            .AddEnvironmentVariables();
    })
    .ConfigureAppConfiguration((hostContext, config) =>
    {
        if (hostContext.HostingEnvironment.IsDevelopment())
        {
            config.AddJsonFile("local.settings.json");
        }
    })
    .ConfigureServices((builder, services) =>
    {
        IConfiguration configuration = new ConfigurationBuilder()
            .SetBasePath(Directory.GetCurrentDirectory())
            .AddJsonFile("local.settings.json", optional: true, reloadOnChange: false)
            .AddEnvironmentVariables()
            .Build();

        var settings = new AppSettings();
        configuration.GetSection(nameof(AppSettings)).Bind(settings);
        services.AddSingleton(settings);
        
        services.AddSingleton<IContractPdfGenerator, AssembleDecorationContractPdfGenerator>();
    })
    .Build();

QuestPDF.Settings.License = LicenseType.Community;

host.Run();
