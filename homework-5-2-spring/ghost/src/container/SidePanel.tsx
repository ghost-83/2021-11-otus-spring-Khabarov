import {FC} from "react"
import {Box, Divider, Drawer, List, ListItem, ListItemIcon, Toolbar, Tooltip} from "@mui/material"
import {AutoStories, Settings, LensBlur} from '@mui/icons-material'
import {Link} from "react-router-dom"

const drawerWidth = 60

export const SidePanel: FC = () => {
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
                        <Tooltip title="Reference book" placement="right" arrow>
                            <Link to={'/book'}>
                                <ListItemIcon style={{minWidth: 40}}>
                                    <AutoStories/>
                                </ListItemIcon>
                            </Link>
                        </Tooltip>
                    </ListItem>
                </List>
                <Divider/>
                <List>
                    <ListItem button>
                        <Tooltip title="Settings" placement="right" arrow>
                            <Link to={'/settings'}>
                                <ListItemIcon style={{minWidth: 40}}>
                                    <Settings/>
                                </ListItemIcon>
                            </Link>
                        </Tooltip>
                    </ListItem>
                </List>
            </Box>
        </Drawer>
    )
}