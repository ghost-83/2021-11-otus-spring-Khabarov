import {FC} from "react";
import {Typography} from "@mui/material";


export const NotFound: FC = () => {

    return (
        <Typography paragraph sx={{textAlign: 'center', mt: 12}}>
            <Typography variant="h1">
                404
            </Typography>
            <Typography variant="h2">
                Not Found
            </Typography>
        </Typography>
    )
}