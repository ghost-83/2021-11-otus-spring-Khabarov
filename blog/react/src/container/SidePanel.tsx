import {FC} from "react"
import {Box, Divider, Drawer, List, ListItem, ListItemIcon, Toolbar, Tooltip} from "@mui/material"
import {AutoStories, EventNote, LensBlur} from '@mui/icons-material'
import {Link} from "react-router-dom"
import {useDispatch} from "react-redux";
import {deleteToken, exitAuth} from "../hook/creator/auth.creator";
import Logout from "@mui/icons-material/Logout";

const drawerWidth = 60

export const SidePanel: FC = () => {

    const dispatch = useDispatch()

    const changeHandler = () => {
        dispatch(exitAuth())
        dispatch(deleteToken())
    }

    return (
        <Drawer
            variant="permanent"
            sx={{
                width: drawerWidth,
                flexShrink: 0,
                [`& .MuiDrawer-paper`]: {width: drawerWidth, boxSizing: 'border-box'},
            }}
        >
            <Toolbar/>
            <Box sx={{overflow: 'auto'}}>
                <List>
                    <ListItem button>
                        <Link to={'/'}>
                            <Tooltip title="Home" placement="right" arrow>
                                <ListItemIcon style={{minWidth: 40}}>
                                    <LensBlur/>
                                </ListItemIcon>
                            </Tooltip>
                        </Link>
                    </ListItem>
                    <ListItem button>
                        <Link to={'/post'}>
                            <Tooltip title="Post" placement="right" arrow>
                                <ListItemIcon style={{minWidth: 40}}>
                                    <EventNote/>
                                </ListItemIcon>
                            </Tooltip>
                        </Link>
                    </ListItem>
                    <ListItem button>
                        <Tooltip title="Reference book" placement="right" arrow>
                            <Link to={'/reference-book'}>
                                <ListItemIcon style={{minWidth: 40}}>
                                    <AutoStories/>
                                </ListItemIcon>
                            </Link>
                        </Tooltip>
                    </ListItem>
                </List>
            </Box>
            <Divider />
            <Box>
                <List>
                    <ListItem button>
                        <Tooltip title="Exit" placement="right" arrow onClick={changeHandler}>
                            <ListItemIcon style={{minWidth: 40}}>
                                <Logout/>
                            </ListItemIcon>
                        </Tooltip>
                    </ListItem>
                </List>
            </Box>
        </Drawer>
    )
}