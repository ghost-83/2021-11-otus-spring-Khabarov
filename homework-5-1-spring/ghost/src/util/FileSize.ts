export const fileSize = (size: number): string => {
    if (size / 1000 < 1000)
        return (size / 1000).toFixed(2) + " Kb"
    if (size / 1000000 < 1000)
        return (size / 1000000).toFixed(2) + " Mb"
    return (size / 1000000000).toFixed(2) + " Gb"
}