import {FC} from "react";
import {PythonIcon} from "../../icon/PythonIcon";
import {LinuxIcon} from "../../icon/LinuxIcon";
import {KotlinIcon} from "../../icon/KotlinIcon";
import {ReactIcon} from "../../icon/ReactIcon";
import {ArduinoIcon} from "../../icon/ArduinoIcon";
import {PostgresIcon} from "../../icon/PostgresIcon";
import {SpringIcon} from "../../icon/SpringIcon";
import {RaspberryIcon} from "../../icon/RaspberryIcon";
import {VSCodeIcon} from "../../icon/VSCodeIcon";
import {SSHIcon} from "../../icon/SSHIcon";
import {NginxIcon} from "../../icon/NginxIcon";
import {DockerIcon} from "../../icon/DockerIcon";
import {CIcon} from "../../icon/CIcon";
import {CPPIcon} from "../../icon/CPPIcon";
import {JavaIcon} from "../../icon/JavaIcon";
import {JSIcon} from "../../icon/JSIcon";
import {CssIcon} from "../../icon/CssIcon";
import {JsonIcon} from "../../icon/JsonIcon";

interface Props {
    category: string
}

export const IconCategory: FC<Props> = ({category}) => {
    switch (category) {
        case 'java': return <JavaIcon />
        case 'python': return <PythonIcon />
        case 'linux': return <LinuxIcon />
        case 'kotlin': return <KotlinIcon />
        case 'js': return <JSIcon />
        case 'typescript': return <JSIcon />
        case 'react': return <ReactIcon />
        case 'arduino': return <ArduinoIcon />
        case 'postgres': return <PostgresIcon />
        case 'spring': return <SpringIcon />
        case 'raspberry': return <RaspberryIcon />
        case 'vs_code': return <VSCodeIcon />
        case 'nginx': return <NginxIcon />
        case 'docker': return <DockerIcon />
        case 'android': return <ArduinoIcon />
        case 'c': return <CIcon />
        case 'cpp': return <CPPIcon />
        case 'ssh': return <SSHIcon />
        case 'css': return <CssIcon />
        case 'json': return <JsonIcon />
        default: return <></>
    }
}