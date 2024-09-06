using QuestPDF.Fluent;
using QuestPDF.Helpers;
using QuestPDF.Infrastructure;
using QuestPDF.Previewer;

namespace ContractGenrator;

public class ContractPickUpAndAssemblePdfGenerator
{
    public byte[] Generate()
    {
        return Document.Create(document =>
        {
            document.Page(page =>
            {
                page.Margin(2, Unit.Centimetre);
                page.DefaultTextStyle(x => x.FontFamily("Calibri"));

                InsertHeader(page);

                page.Content()
                    .PaddingVertical(1, Unit.Centimetre)
                    .Column(column =>
                    {
                        column.Spacing(10);

                        column.Item().Text("Entre as partes:").Bold();
                        column.Item().Text(
                            "Locador (a): 53.782.266 LUCIANA DA SILVA VIEIRA ALVES, inscrita no CNPJ 53.782.266/0001-08, em nome de Luciana Alves Decorações, situada no endereço Rua: José Nunes da Silva, 870 - Comerciários - Lins/SP, telefone (14) 99869-8287.");
                        column.Item().Text("Locatário (a): ");

                        column.Item().Text("Objeto do Contrato:").Bold();
                        column.Item()
                            .Text(
                                "A decoração do tema Turma do Mickey no tamanho grande, será montada na Churrasqueira climatizada do Lins Country Club, localizada na Rod. Mal. Rondon, KM 444 - Lins, SP no dia 06/07/2024 as 17:30.");
                        column.Item()
                            .Text(
                                "A decoração será desmontada e recolhida após o término do evento ou em data e horário combinados entre as partes.");

                        column.Item().Text("Termos e Condições:").Bold();
                        column.Item().Text("1. O serviço compreende a montagem da decoração no endereço citado acima.");
                        column.Item()
                            .Text(
                                "2. O atraso na devolução do material implicará em multa de 10% sobre o valor do serviço ou locação.");
                        column.Item().Text("3. O valor do aluguel fica estabelecido em  para a decoração, sendo:");

                        column.Item().PaddingLeft(10).Column(innerColumn =>
                        {
                            innerColumn.Spacing(5);
                            innerColumn.Item()
                                .Text(
                                    "• 30% no ato da assinatura do contrato como sinal de reserva, no valor de          ");
                            innerColumn.Item()
                                .Text("• Os 70% restantes serão pagos no dia da retirada das peças, no valor de ");
                        });

                        column.Item().Text("4. Os valores poderão ser pagos das seguintes formas:");

                        column.Item().PaddingLeft(10).Column(innerColumn =>
                        {
                            innerColumn.Spacing(5);
                            innerColumn.Item().Text("• Via Pix para:");
                            innerColumn.Item().PaddingLeft(10).Text("Chave: 174.078.978-40");
                            innerColumn.Item().PaddingLeft(10).Text("Nome: Luciana da S. V. Alves");
                            innerColumn.Item().PaddingLeft(10).Text("Banco: Nubank S.A.");
                            innerColumn.Item()
                                .Text(
                                    "• Via cartão de crédito em até 12x, com as taxas de parcelamento por conta do locatário.");
                        });
                    });
                Footer(page);
            });

            document.Page(page =>
            {
                page.Margin(2, Unit.Centimetre);
                page.DefaultTextStyle(x => x.FontFamily("Calibri"));

                InsertHeader(page);

                page.Content()
                    .PaddingVertical(1, Unit.Centimetre)
                    .Column(column =>
                    {
                        column.Spacing(10);
                        column.Item().Text("5. Os serviços ou produtos discriminados abaixo estão inclusos:");
                        column.Item().PaddingBottom(1, Unit.Centimetre)
                            .Element(ComposeTable); // Adiciona a tabela dinâmica

                        column.Item().Text("6. No caso de desistência, não haverá devolução da entrada.");
                        column.Item()
                            .Text(
                                "7. Trocas de data devem ser feitas com 30 dias de antecedência, sujeitas à disponibilidade.");
                        column.Item().Text(
                            "8. Painéis, mobiliário, cortinado, toalhas ou qualquer material utilizado para a decoração devem ser devolvidos em perfeito estado. Em caso de estragos ou mau uso, será cobrado o valor referente ao patrimônio no mercado.");
                        column.Item()
                            .Text("9. A partir da assinatura do contrato, não serão toleradas trocas de materiais.");
                        column.Item().Text("10. Autorizo a divulgação das imagens da decoração.");
                        column.Item()
                            .Text(
                                "11. As partes comprometem-se a cumprir horários determinados e datas, ficando o foro da cidade de Lins como competente para qualquer questão oriunda deste contrato.");

                        column.Item().ShowEntire().Column(column =>
                        {
                            column.Item().PaddingTop(1, Unit.Centimetre)
                                .Text($"Lins {DateTime.UtcNow:dd 'd'e MMMM 'd'e yyyy}");

                            column.Item().PaddingTop(2, Unit.Centimetre).Row(row =>
                            {
                                row.RelativeItem()
                                    .Text(
                                        "________________________________\nLocador (a): Luciana da S. V. Alves\nCPF: 174.078.978-40");
                                row.RelativeItem().Text("________________________________\nLocatário (a): \nCPF:");
                            });
                        });
                    });

                Footer(page);
            });
        }).GeneratePdf();
    }


    private void ComposeTable(IContainer container)
    {
        // Dados de exemplo para a tabela
        var items = new[]
        {
            new { Numero = 1, Descricao = "Item de Exemplo 1", Quantidade = 10 },
            new { Numero = 2, Descricao = "Item de Exemplo 2", Quantidade = 5 },
            new { Numero = 2, Descricao = "Item de Exemplo 2", Quantidade = 5 },
            new { Numero = 2, Descricao = "Item de Exemplo 2", Quantidade = 5 },
        };

        container.Table(table =>
        {
            table.ColumnsDefinition(columns =>
            {
                columns.ConstantColumn(50); // Número do Item
                columns.RelativeColumn(); // Descrição
                columns.ConstantColumn(70); // Quantidade
            });

            table.Header(header =>
            {
                header.Cell().Element(CellStyle).AlignCenter().Text("Item");
                header.Cell().Element(CellStyle).AlignCenter().Text("Descrição");
                header.Cell().Element(CellStyle).AlignCenter().Text("Quantidade");

                static IContainer CellStyle(IContainer container) =>
                    container.DefaultTextStyle(x => x.Bold()).Padding(5).BorderBottom(1).BorderColor(Colors.Black);
            });

            foreach (var item in items)
            {
                table.Cell().Element(CellStyle).AlignCenter().Text(item.Numero.ToString());
                table.Cell().Element(CellStyle).Text(item.Descricao);
                table.Cell().Element(CellStyle).AlignCenter().Text(item.Quantidade.ToString());

                static IContainer CellStyle(IContainer container) =>
                    container.Padding(5).BorderBottom(1).BorderColor(Colors.Grey.Lighten2);
            }
        });
    }

    private void InsertHeader(PageDescriptor pageDescriptor)
    {
        pageDescriptor.Header().Column(column =>
        {
            column.Item().Row(row =>
            {
                row.ConstantItem(80).Image("logo.png").FitArea();

                row.RelativeItem().AlignCenter().Column(innerColumn =>
                {
                    innerColumn.Item().Text("Luciana Alves Decorações").FontSize(20).Bold();
                    innerColumn.Item().Text("Contrato de Prestação de Serviço \npara Montagem de Decoração")
                        .FontSize(16);
                });
            });

            column.Item().PaddingTop(10).LineHorizontal(1).LineColor(Colors.Grey.Lighten2);
        });
    }

    private void Footer(PageDescriptor pageDescriptor)
    {
        pageDescriptor.Footer().Column(column =>
        {
            // make footer with contact information and page number
            column.Item().Text("Luciana Alves Decorações").FontSize(10).AlignCenter();
            column.Item().Text("Rua: José Nunes da Silva, 870 - Comerciários - Lins/SP").FontSize(8).AlignCenter();
            column.Item().Text("Telefone: (14) 99869-8287").FontSize(8).AlignCenter();

            column.Item().AlignRight().Text(text =>
            {
                text.Span("Página ");
                text.CurrentPageNumber();
                text.Span(" de ");
                text.TotalPages();
            });
        });
    }
}