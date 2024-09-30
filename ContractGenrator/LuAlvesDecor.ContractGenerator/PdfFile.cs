using System.Globalization;
using System.Text;
using System.Text.RegularExpressions;
using ContractGenrator.Helpers;

namespace ContractGenrator;

public partial class PdfFile
{
    public PdfFile(byte[] bytes, string fileName)
    {
        Bytes = bytes;
        FileName = $"{fileName}-{DateTimeHelper.BrazilNow:dd-MM-yyyy}.pdf";
    }

    public readonly string ContentType = "application/pdf";
        
    public string FileName { get; }
    public byte[] Bytes { get; }

    public string GetFileName()
    {
        return MyRegex().Replace(FileName, ConvertToAscii);
    }
        
    private static string ConvertToAscii(Match match)
    {
        return string.Concat(match.Value.Normalize(NormalizationForm.FormD).Where(ch => char.GetUnicodeCategory(ch) != UnicodeCategory.NonSpacingMark));
    }

    [GeneratedRegex(@"[^\u0000-\u007F]")]
    private static partial Regex MyRegex();
}