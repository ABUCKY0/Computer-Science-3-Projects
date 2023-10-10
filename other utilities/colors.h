#ifndef COLORS_H
#define COLORS_H

#include <string>
class Colors {
public:
    // Regular Colors
    static const std::string BLACK;
    static const std::string RED;
    static const std::string GREEN;
    static const std::string YELLOW;
    static const std::string BLUE;
    static const std::string MAGENTA;
    static const std::string CYAN;
    static const std::string WHITE;
    // Bold Colors
    static const std::string BBLACK;
    static const std::string BRED;
    static const std::string BGREEN;
    static const std::string BYELLOW;
    static const std::string BBLUE;
    static const std::string BMAGENTA;
    static const std::string BCYAN;
    static const std::string BWHITE;
    // Underline
    static const std::string UBLACK;
    static const std::string URED;
    static const std::string UGREEN;
    static const std::string UYELLOW;
    static const std::string UBLUE;
    static const std::string UMAGENTA;
    static const std::string UCYAN;
    static const std::string UWHITE;
    // Background
    static const std::string ONBLACK;
    static const std::string ONRED;
    static const std::string ONGREEN;
    static const std::string ONYELLOW;
    static const std::string ONBLUE;
    static const std::string ONMAGENTA;
    static const std::string ONCYAN;
    static const std::string ONWHITE;
    // High Intensity
    static const std::string IBLACK;
    static const std::string IRED;
    static const std::string IGREEN;
    static const std::string IYELLOW;
    static const std::string IBLUE;
    static const std::string IMAGENTA;
    static const std::string ICYAN;
    static const std::string IWHITE;
    // Bold High Intensity
    static const std::string BIBLACK;
    static const std::string BIRED;
    static const std::string BIGREEN;
    static const std::string BIYELLOW;
    static const std::string BIBLUE;
    static const std::string BIMAGENTA;
    static const std::string BICYAN;
    static const std::string BIWHITE;
    // High Intensity backgrounds
    static const std::string ONIBLACK;
    static const std::string ONIRED;
    static const std::string ONIGREEN;
    static const std::string ONIYELLOW;
    static const std::string ONIBLUE;
    static const std::string ONIMAGENTA;
    static const std::string ONICYAN;
    static const std::string ONIWHITE;
    // Reset
    static const std::string RESET;

    static std::string colorize(std::string color, std::string text) {
        return color + text + RESET;
    }
    static std::string CUSTOMRGB(short red, short green, short blue) {
        return "\033[38;2;" + std::to_string(red) + ";" + std::to_string(green) + ";" + std::to_string(blue) + "m";
    }
};

 const std::string Colors::RESET = "\033[0m";
 const std::string Colors::BLACK = "\033[30m";
 const std::string Colors::RED = "\033[31m";
 const std::string Colors::GREEN = "\033[32m";
 const std::string Colors::YELLOW = "\033[33m";
 const std::string Colors::BLUE = "\033[34m";
 const std::string Colors::MAGENTA = "\033[35m";
 const std::string Colors::CYAN = "\033[36m";
 const std::string Colors::WHITE = "\033[37m";
 const std::string Colors::BBLACK = "\033[1m\033[30m";
 const std::string Colors::BRED = "\033[1m\033[31m";
 const std::string Colors::BGREEN = "\033[1m\033[32m";
 const std::string Colors::BYELLOW = "\033[1m\033[33m";
 const std::string Colors::BBLUE = "\033[1m\033[34m";
 const std::string Colors::BMAGENTA = "\033[1m\033[35m";
 const std::string Colors::BCYAN = "\033[1m\033[36m";
 const std::string Colors::BWHITE = "\033[1m\033[37m";
 const std::string Colors::UBLACK = "\033[4m\033[30m";
 const std::string Colors::URED = "\033[4m\033[31m";
 const std::string Colors::UGREEN = "\033[4m\033[32m";
 const std::string Colors::UYELLOW = "\033[4m\033[33m";
 const std::string Colors::UBLUE = "\033[4m\033[34m";
 const std::string Colors::UMAGENTA = "\033[4m\033[35m";
 const std::string Colors::UCYAN = "\033[4m\033[36m";
 const std::string Colors::UWHITE = "\033[4m\033[37m";
 const std::string Colors::ONBLACK = "\033[40m";
 const std::string Colors::ONRED = "\033[41m";
 const std::string Colors::ONGREEN = "\033[42m";
 const std::string Colors::ONYELLOW = "\033[43m";
 const std::string Colors::ONBLUE = "\033[44m";
 const std::string Colors::ONMAGENTA = "\033[45m";
 const std::string Colors::ONCYAN = "\033[46m";
 const std::string Colors::ONWHITE = "\033[47m";
 const std::string Colors::IBLACK = "\033[0;90m";
 const std::string Colors::IRED = "\033[0;91m";
 const std::string Colors::IGREEN = "\033[0;92m";
 const std::string Colors::IYELLOW = "\033[0;93m";
 const std::string Colors::IBLUE = "\033[0;94m";
 const std::string Colors::IMAGENTA = "\033[0;95m";
 const std::string Colors::ICYAN = "\033[0;96m";
 const std::string Colors::IWHITE = "\033[0;97m";
 const std::string Colors::BIBLACK = "\033[1;90m";
 const std::string Colors::BIRED = "\033[1;91m";
 const std::string Colors::BIGREEN = "\033[1;92m";
 const std::string Colors::BIYELLOW = "\033[1;93m";
 const std::string Colors::BIBLUE = "\033[1;94m";
 const std::string Colors::BIMAGENTA = "\033[1;95m";
 const std::string Colors::BICYAN = "\033[1;96m";
 const std::string Colors::BIWHITE = "\033[1;97m";
 const std::string Colors::ONIBLACK = "\033[0;100m";
 const std::string Colors::ONIRED = "\033[0;101m";
 const std::string Colors::ONIGREEN = "\033[0;102m";
 const std::string Colors::ONIYELLOW = "\033[0;103m";
 const std::string Colors::ONIBLUE = "\033[0;104m";
 const std::string Colors::ONIMAGENTA = "\033[0;105m";
 const std::string Colors::ONICYAN = "\033[0;106m";
 const std::string Colors::ONIWHITE = "\033[0;107m";

#endif