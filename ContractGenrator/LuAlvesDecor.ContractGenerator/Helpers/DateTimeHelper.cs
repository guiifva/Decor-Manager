using System.Runtime.InteropServices;

namespace ContractGenrator.Helpers
{
    public static class DateTimeHelper
    {
        public static DateTime BrazilNow => DateTime.Now.ToBrazilTime();

        public static DateTime ToBrazilTime(this DateTime date)
        {
            var brazilTimeZoneInfo = TimeZoneInfo.Local;
            if (RuntimeInformation.IsOSPlatform(OSPlatform.Windows))
            {
                brazilTimeZoneInfo = TimeZoneInfo.FindSystemTimeZoneById("E. South America Standard Time");
            }
            if (RuntimeInformation.IsOSPlatform(OSPlatform.Linux))
            {
                brazilTimeZoneInfo = TimeZoneInfo.FindSystemTimeZoneById("America/Sao_Paulo");
            }
            
            return TimeZoneInfo.ConvertTime(date, TimeZoneInfo.Local, brazilTimeZoneInfo);
        }

        public static DateTime ToTimeZone(this DateTime date, string timeZoneId)
        {
            var validTimeZone = TimeZoneInfo.GetSystemTimeZones().Any(x => x.Id == timeZoneId);

            if (!validTimeZone) throw new InvalidTimeZoneException();
            
            var brazilTimeZoneInfo = TimeZoneInfo.FindSystemTimeZoneById(timeZoneId);
            return TimeZoneInfo.ConvertTime(date, TimeZoneInfo.Local, brazilTimeZoneInfo);
        }
    }
}
