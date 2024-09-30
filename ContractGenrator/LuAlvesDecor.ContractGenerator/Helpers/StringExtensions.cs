namespace ContractGenrator.Helpers;

public static class StringExtensions
{
    public static string FormatCpf(this string cpf)
    {
        if (string.IsNullOrWhiteSpace(cpf) || cpf.Length != 11)
        {
            return cpf; // Retorna o CPF original caso esteja mal formatado ou incompleto
        }

        // Formata o CPF no padrão 000.000.000-00
        return $"{cpf.Substring(0, 3)}.{cpf.Substring(3, 3)}.{cpf.Substring(6, 3)}-{cpf.Substring(9, 2)}";
    }

    public static string FormatPhoneNumber(this string phoneNumber)
    {
        if (string.IsNullOrWhiteSpace(phoneNumber))
        {
            return phoneNumber; // Retorna o número original se estiver vazio ou nulo
        }

        // Remove caracteres que não são números
        var digitsOnly = new string(phoneNumber.Where(char.IsDigit).ToArray());

        if (digitsOnly.Length == 11) // Exemplo: telefone celular no formato (00) 00000-0000
        {
            return $"({digitsOnly.Substring(0, 2)}) {digitsOnly.Substring(2, 5)}-{digitsOnly.Substring(7, 4)}";
        }
        else if (digitsOnly.Length == 10) // Exemplo: telefone fixo no formato (00) 0000-0000
        {
            return $"({digitsOnly.Substring(0, 2)}) {digitsOnly.Substring(2, 4)}-{digitsOnly.Substring(6, 4)}";
        }

        return phoneNumber; // Retorna o número original caso não seja de 10 ou 11 dígitos
    }
}